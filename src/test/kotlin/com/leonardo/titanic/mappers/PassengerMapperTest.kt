package com.leonardo.titanic.mappers

import com.leonardo.titanic.dto.Passenger
import com.leonardo.titanic.entities.PassengerEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PassengerMapperTest {

    @Test
    fun `should map PassengerEntity to Passenger dto correctly`() {
        val entity = PassengerEntity(
            passengerId = 1,
            survived = 1,
            pclass = 1,
            name = "John Test",
            sex = "male",
            age = 30.0,
            sibSp = 0,
            parch = 0,
            ticket = "ABC123",
            fare = 50.0,
            cabin = "C85",
            embarked = "S"
        )

        val dto = PassengerMapper.toDto(entity)

        assertEquals(entity.passengerId, dto.passengerId)
        assertEquals(entity.survived, dto.survived)
        assertEquals(entity.pclass, dto.pclass)
        assertEquals(entity.name, dto.name)
        assertEquals(entity.sex, dto.sex)
        assertEquals(entity.age, dto.age)
        assertEquals(entity.sibSp, dto.sibSp)
        assertEquals(entity.parch, dto.parch)
        assertEquals(entity.ticket, dto.ticket)
        assertEquals(entity.fare, dto.fare)
        assertEquals(entity.cabin, dto.cabin)
        assertEquals(entity.embarked, dto.embarked)
    }
}
