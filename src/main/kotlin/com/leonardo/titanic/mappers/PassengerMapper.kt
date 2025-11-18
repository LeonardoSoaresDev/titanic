package com.leonardo.titanic.mappers

import com.leonardo.titanic.dto.Passenger
import com.leonardo.titanic.entities.PassengerEntity

/**
 * Mapper object responsible for converting [PassengerEntity] objects
 * into [Passenger] DTOs.
 *
 * This is useful for exposing passenger data through the API
 * without exposing the database entities directly.
 */
object PassengerMapper {

    /**
     * Converts a [PassengerEntity] to a [Passenger] DTO.
     *
     * @param entity the [PassengerEntity] instance to be converted
     * @return a [Passenger] DTO containing the same data as the entity
     */
    fun toDto(entity: PassengerEntity): Passenger =
        Passenger(
            passengerId = entity.passengerId,
            survived = entity.survived,
            pclass = entity.pclass,
            name = entity.name,
            sex = entity.sex,
            age = entity.age,
            sibSp = entity.sibSp,
            parch = entity.parch,
            ticket = entity.ticket,
            fare = entity.fare,
            cabin = entity.cabin,
            embarked = entity.embarked
        )
}
