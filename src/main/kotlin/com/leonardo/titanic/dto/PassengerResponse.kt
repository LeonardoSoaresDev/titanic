package com.leonardo.titanic.dto

/**
 * Represents a paginated response for passenger data.
 *
 * This DTO contains the paginated list of passengers along with metadata
 * describing the pagination state.
 *
 * @property content The list of passengers for the current page.
 * @property page The current page number (0-based index).
 * @property size The number of elements per page.
 * @property totalPages The total number of available pages.
 * @property totalElements The total number of passengers matching the filters.
 * @property hasNext Indicates whether there is a next page available.
 * @property hasPrevious Indicates whether there is a previous page available.
 */
data class PassengerResponse(
    val content: List<Passenger>,
    val page: Int,
    val size: Int,
    val totalPages: Int,
    val totalElements: Long,
    val hasNext: Boolean,
    val hasPrevious: Boolean
)
