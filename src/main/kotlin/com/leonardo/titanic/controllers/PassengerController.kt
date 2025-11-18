package com.leonardo.titanic.controllers

import com.leonardo.titanic.dto.Passenger
import com.leonardo.titanic.dto.PassengerResponse
import com.leonardo.titanic.enums.PassengerClass
import com.leonardo.titanic.enums.Survived
import com.leonardo.titanic.services.PassengerService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Controller for managing Titanic passenger data.
 */
@RestController
class PassengerController(private val passengerService: PassengerService) {

    /**
     * Returns a paginated list of passengers with optional filters.
     *
     * @param survived Optional filter for survival status (0 = did not survive, 1 = survived).
     * @param pclass Optional filter for passenger class (1, 2, or 3).
     * @param pageable Optional pageable information
     *
     * @return A [PassengerResponse] containing the filtered and paginated passenger data.
     */
    @GetMapping("/passengers")
    fun passengers(
        @RequestParam(required = false) survived: Int?,
        @RequestParam(required = false) pclass: Int?,
        pageable: Pageable
    ): PassengerResponse {

        val survivedEnum = Survived.fromCode(survived)
        val pclassEnum = PassengerClass.fromCode(pclass)

        return passengerService.getPassengers(
            survivedEnum,
            pclassEnum,
            pageable
        )
    }

    /**
     * Returns a passenger based on the given ID or an exception if the passenger was not found.
     *
     * @param id Passenger ID
     * @return A [Passenger]
     */
    @GetMapping("/passengers/{id}")
    fun getPassengerById(@PathVariable id: Long): Passenger {
        return passengerService.getPassengerById(id)
    }
}
