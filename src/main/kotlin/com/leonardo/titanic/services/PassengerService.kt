package com.leonardo.titanic.services

import com.leonardo.titanic.dto.Passenger
import com.leonardo.titanic.dto.PassengerResponse
import com.leonardo.titanic.entities.PassengerEntity
import com.leonardo.titanic.enums.PassengerClass
import com.leonardo.titanic.enums.Survived
import com.leonardo.titanic.mappers.PassengerMapper
import com.leonardo.titanic.repositories.PassengerRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * Service class that handles passenger-related operations,
 */
@Service
class PassengerService(
    private val passengerRepository: PassengerRepository
) {
    /**
     * Retrieves passengers using optional filters and pagination.
     *
     * @param survived Optional survival filter (1 = survived, 0 = did not survive).
     * @param pclass Optional filter for passenger class (1–3).
     * @param pageRequest Pagination settings.
     * @return A structured paginated response containing passenger data.
     */
    @Cacheable(
        value = ["passengers"],
        key = "#survived?.code + '-' + #pclass?.code + '-' + #pageRequest.pageNumber + '-' + #pageRequest.pageSize"
    )
    fun getPassengers(
        survived: Survived?,
        pclass: PassengerClass?,
        pageRequest: Pageable
    ): PassengerResponse {
        val page = passengerRepository.findWithFilters(survived?.code, pclass?.code, pageRequest)
        return from(page)
    }

    private fun from(page: Page<PassengerEntity>): PassengerResponse {
        return PassengerResponse(
            content = page.content.map { PassengerMapper.toDto(it) },
            page = page.number,
            size = page.size,
            totalPages = page.totalPages,
            totalElements = page.totalElements,
            hasNext = page.hasNext(),
            hasPrevious = page.hasPrevious()
        )
    }

    /**
     * Retrieves a passenger based on the given ID
     *
     * @param id Passenger ID
     * @throws NoSuchElementException If the passenger was not found in the database.
     */
    fun getPassengerById(id: Long): Passenger {
        return passengerRepository.findById(id)
            .map { PassengerMapper.toDto(it) }
            .orElseThrow { NoSuchElementException("Passenger with ID $id not found") }
    }
}
