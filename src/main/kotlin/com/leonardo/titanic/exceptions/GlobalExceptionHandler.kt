package com.leonardo.titanic.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Global exception handler for the Titanic API.
 *
 * Handles specific exceptions thrown in the application and
 * returns a standardized [ErrorResponse] with an appropriate HTTP status code.
 */
@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * Handles [IllegalArgumentException] thrown in the application.
     *
     * @param exception The [IllegalArgumentException] instance
     * @return A [ResponseEntity] containing an [ErrorResponse] with status 400 BAD REQUEST
     */
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            message = exception.message ?: "Invalid data",
            status = HttpStatus.BAD_REQUEST
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    /**
     * Handles [NoSuchElementException] thrown when an element is not found.
     *
     * @param exception The [NoSuchElementException] instance
     * @return A [ResponseEntity] containing an [ErrorResponse] with status 404 NOT FOUND
     */
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(exception: NoSuchElementException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            message = exception.message ?: "Element not found",
            status = HttpStatus.NOT_FOUND
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
}
