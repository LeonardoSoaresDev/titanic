package com.leonardo.titanic.services

import com.leonardo.titanic.entities.PassengerEntity
import com.leonardo.titanic.repositories.PassengerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PassengerService(
    private val passengerRepository: PassengerRepository
) {
    fun getPassengers(
        survived: Int?,
        pclass: Int?,
        page: Int,
        size: Int
    ): Page<PassengerEntity> {

        val pageable = PageRequest.of(page, size)

        return when {
            survived != null && pclass != null ->
                passengerRepository.findBySurvivedAndPclass(survived, pclass, pageable)

            survived != null ->
                passengerRepository.findBySurvived(survived, pageable)

            pclass != null ->
                passengerRepository.findByPclass(pclass, pageable)

            else ->
                passengerRepository.findAll(pageable)
        }
    }
}
