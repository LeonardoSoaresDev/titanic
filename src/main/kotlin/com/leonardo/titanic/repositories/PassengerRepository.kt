package com.leonardo.titanic.repositories

import com.leonardo.titanic.entities.PassengerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PassengerRepository : JpaRepository<PassengerEntity, Long> {

    fun findBySurvived(survived: Int, pageable: Pageable): Page<PassengerEntity>

    fun findByPclass(pclass: Int, pageable: Pageable): Page<PassengerEntity>

    fun findBySurvivedAndPclass(survived: Int, pclass: Int, pageable: Pageable): Page<PassengerEntity>
}
