package com.leonardo.titanic.controllers

import com.leonardo.titanic.entities.PassengerEntity
import com.leonardo.titanic.services.PassengerService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TitanicController(private val passengerService: PassengerService) {

    @GetMapping("/passengers")
    fun passengers(
        @RequestParam(required = false) survived: Int?,
        @RequestParam(required = false) pclass: Int?,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<PassengerEntity> {
        return passengerService.getPassengers(survived, pclass, page, size);
    }
}
