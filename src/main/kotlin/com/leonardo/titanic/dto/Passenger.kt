package com.leonardo.titanic.dto

/**
 * Data Transfer Object representing a passenger extracted from the Titanic dataset.
 *
 * This DTO is used to expose only the necessary and safe fields to the API consumer,
 * preventing direct exposure of JPA entities and ensuring clear separation between
 * persistence and presentation layers.
 */
data class Passenger(
    val passengerId: Long,
    val survived: Int,
    val pclass: Int,
    val name: String,
    val sex: String,
    val age: Double?,
    val sibSp: Int,
    val parch: Int,
    val ticket: String,
    val fare: Double?,
    val cabin: String?,
    val embarked: String?
)
