package com.leonardo.titanic.exceptions

import org.springframework.http.HttpStatusCode

/**
 * Represents a standardized error response for the API.
 *
 * This class is used to return meaningful error messages along with
 * the corresponding HTTP status code to the client.
 *
 * @property message Descriptive error message
 * @property status HTTP status code associated with the error
 */
data class ErrorResponse(
    val message: String,
    val status: HttpStatusCode
)
