package com.leonardo.titanic.services

import com.leonardo.titanic.entities.PassengerEntity
import com.leonardo.titanic.enums.PassengerClass
import com.leonardo.titanic.enums.Survived
import com.leonardo.titanic.repositories.PassengerRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*

/**
 * Unit tests for PassengerService.
 */
class PassengerServiceTest {

    private lateinit var passengerRepository: PassengerRepository
    private lateinit var passengerService: PassengerService
    private lateinit var passengerEntity: PassengerEntity
    private lateinit var pageRequest: PageRequest

    @BeforeEach
    fun setUp() {
        passengerRepository = mock()
        passengerService = PassengerService(passengerRepository)

        passengerEntity = PassengerEntity(
            passengerId = 1L,
            survived = 1,
            pclass = 1,
            name = "John Doe",
            sex = "male",
            age = 30.0,
            sibSp = 0,
            parch = 0,
            ticket = "A123",
            fare = 50.0,
            cabin = "C23",
            embarked = "S"
        )

        pageRequest = PageRequest.of(0, 10)
    }

    @Test
    fun `getPassengers should return paginated response when no filters are applied`() {
        val passengers = listOf(passengerEntity)
        val page = PageImpl(passengers, pageRequest, 1)

        whenever(passengerRepository.findWithFilters(isNull(), isNull(), eq(pageRequest)))
            .thenReturn(page)

        val result = passengerService.getPassengers(null, null, pageRequest)

        assertNotNull(result)
        assertEquals(1, result.content.size)
        assertEquals(1, result.totalElements)
        assertEquals(0, result.page)
        assertEquals(10, result.size)
        assertEquals(1, result.totalPages)
        assertFalse(result.hasNext)
        assertFalse(result.hasPrevious)

        verify(passengerRepository, times(1)).findWithFilters(isNull(), isNull(), eq(pageRequest))
    }

    @Test
    fun `getPassengers should apply survived and pclass filters correctly`() {
        val passengers = listOf(passengerEntity)
        val page = PageImpl(passengers, pageRequest, 1)
        val survived = Survived.YES
        val pclass = PassengerClass.FIRST

        whenever(passengerRepository.findWithFilters(eq(1), eq(1), eq(pageRequest)))
            .thenReturn(page)

        val result = passengerService.getPassengers(survived, pclass, pageRequest)

        assertNotNull(result)
        assertEquals(1, result.totalElements)
        verify(passengerRepository).findWithFilters(eq(1), eq(1), eq(pageRequest))
    }

    @Test
    fun `getPassengers should filter by survived status only`() {
        val passengers = listOf(passengerEntity)
        val page = PageImpl(passengers, pageRequest, 1)
        val survived = Survived.NO

        whenever(passengerRepository.findWithFilters(eq(0), isNull(), eq(pageRequest)))
            .thenReturn(page)

        val result = passengerService.getPassengers(survived, null, pageRequest)

        assertNotNull(result)
        assertEquals(1, result.content.size)
        verify(passengerRepository).findWithFilters(eq(0), isNull(), eq(pageRequest))
    }

    @Test
    fun `getPassengers should filter by passenger class only`() {
        val passengers = listOf(passengerEntity)
        val page = PageImpl(passengers, pageRequest, 1)
        val pclass = PassengerClass.SECOND

        whenever(passengerRepository.findWithFilters(isNull(), eq(2), eq(pageRequest)))
            .thenReturn(page)

        val result = passengerService.getPassengers(null, pclass, pageRequest)

        assertNotNull(result)
        assertEquals(1, result.content.size)
        verify(passengerRepository).findWithFilters(isNull(), eq(2), eq(pageRequest))
    }

    @Test
    fun `getPassengers should return empty response when no passengers match criteria`() {
        val emptyPage = PageImpl<PassengerEntity>(emptyList(), pageRequest, 0)

        whenever(passengerRepository.findWithFilters(isNull(), isNull(), eq(pageRequest)))
            .thenReturn(emptyPage)

        val result = passengerService.getPassengers(null, null, pageRequest)

        assertNotNull(result)
        assertTrue(result.content.isEmpty())
        assertEquals(0, result.totalElements)
        assertEquals(0, result.totalPages)
        assertFalse(result.hasNext)
        assertFalse(result.hasPrevious)
    }

    @Test
    fun `getPassengers should correctly set pagination metadata for middle page`() {
        val passengers = listOf(passengerEntity)
        val pageRequestMiddle = PageRequest.of(1, 10)
        val page = PageImpl(passengers, pageRequestMiddle, 25) // 3 pages total

        whenever(passengerRepository.findWithFilters(isNull(), isNull(), eq(pageRequestMiddle)))
            .thenReturn(page)

        val result = passengerService.getPassengers(null, null, pageRequestMiddle)

        assertEquals(1, result.page)
        assertEquals(3, result.totalPages)
        assertEquals(25, result.totalElements)
        assertTrue(result.hasNext)
        assertTrue(result.hasPrevious)
    }

    @Test
    fun `getPassengers should correctly set pagination metadata for first page`() {
        val passengers = listOf(passengerEntity)
        val page = PageImpl(passengers, pageRequest, 25)

        whenever(passengerRepository.findWithFilters(isNull(), isNull(), eq(pageRequest)))
            .thenReturn(page)

        val result = passengerService.getPassengers(null, null, pageRequest)

        assertEquals(0, result.page)
        assertTrue(result.hasNext)
        assertFalse(result.hasPrevious)
    }

    @Test
    fun `getPassengers should correctly set pagination metadata for last page`() {
        val passengers = listOf(passengerEntity)
        val lastPageRequest = PageRequest.of(2, 10)
        val page = PageImpl(passengers, lastPageRequest, 25)

        whenever(passengerRepository.findWithFilters(isNull(), isNull(), eq(lastPageRequest)))
            .thenReturn(page)

        val result = passengerService.getPassengers(null, null, lastPageRequest)

        assertEquals(2, result.page)
        assertFalse(result.hasNext)
        assertTrue(result.hasPrevious)
    }

    @Test
    fun `getPassengerById should return passenger when found`() {
        val passengerId = 1L

        whenever(passengerRepository.findById(passengerId))
            .thenReturn(Optional.of(passengerEntity))

        val result = passengerService.getPassengerById(passengerId)

        assertNotNull(result)
        assertEquals(passengerId, result.passengerId)
        assertEquals("John Doe", result.name)
        assertEquals(1, result.survived)
        assertEquals(1, result.pclass)
        assertEquals("male", result.sex)
        assertEquals(30.0, result.age)

        verify(passengerRepository, times(1)).findById(passengerId)
    }

    @Test
    fun `getPassengerById should throw NoSuchElementException when passenger not found`() {
        val passengerId = 999L

        whenever(passengerRepository.findById(passengerId))
            .thenReturn(Optional.empty())

        val exception = assertThrows<NoSuchElementException> {
            passengerService.getPassengerById(passengerId)
        }

        assertEquals("Passenger with ID $passengerId not found", exception.message)
        verify(passengerRepository, times(1)).findById(passengerId)
    }

    @Test
    fun `getPassengerById should map entity fields correctly`() {
        val passengerId = 1L
        val entityWithAllFields = PassengerEntity(
            passengerId = passengerId,
            survived = 0,
            pclass = 3,
            name = "Jane Smith",
            sex = "female",
            age = 25.5,
            sibSp = 2,
            parch = 1,
            ticket = "B456",
            fare = 75.25,
            cabin = "D15",
            embarked = "C"
        )

        whenever(passengerRepository.findById(passengerId))
            .thenReturn(Optional.of(entityWithAllFields))

        val result = passengerService.getPassengerById(passengerId)

        assertEquals(passengerId, result.passengerId)
        assertEquals("Jane Smith", result.name)
        assertEquals(0, result.survived)
        assertEquals(3, result.pclass)
        assertEquals("female", result.sex)
        assertEquals(25.5, result.age)
        assertEquals(2, result.sibSp)
        assertEquals(1, result.parch)
        assertEquals("B456", result.ticket)
        assertEquals(75.25, result.fare)
        assertEquals("D15", result.cabin)
        assertEquals("C", result.embarked)
    }
}
