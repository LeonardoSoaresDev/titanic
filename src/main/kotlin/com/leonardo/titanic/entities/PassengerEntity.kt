package com.leonardo.titanic.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

/**
 * JPA entity representing a Titanic passenger.
 *
 * Maps to the "passengers" table in the database and stores
 * all relevant passenger information.
 */
@Entity
@Table(name = "passengers")
data class PassengerEntity(

    @Id
    @Column(name = "passenger_id")
    val passengerId: Long = 0,
    val survived: Int = 0,
    val pclass: Int = 0,
    val name: String = "",
    val sex: String = "",
    val age: Double? = null,
    @Column(name = "sibsp")
    val sibSp: Int = 0,
    val parch: Int = 0,
    val ticket: String = "",
    val fare: Double? = null,
    val cabin: String? = null,
    val embarked: String? = null
)
