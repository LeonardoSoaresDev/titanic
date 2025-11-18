package com.leonardo.titanic.repositories

import com.leonardo.titanic.entities.PassengerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Repository interface for accessing Titanic passenger data.
 */
interface PassengerRepository : JpaRepository<PassengerEntity, Long> {

    /**
     * Retrieves a paginated list of passengers with optional filters.
     *
     * @param survived Optional filter for survival status (0 = did not survive, 1 = survived).
     * @param pclass Optional filter for passenger class (1, 2, or 3).
     * @param pageable Pagination information including page number and size.
     *
     * @return A [Page] of [PassengerEntity] matching the provided filters.
     */
    @Query(
        """
        SELECT p FROM PassengerEntity p
        WHERE (:survived IS NULL OR p.survived = :survived)
          AND (:pclass IS NULL OR p.pclass = :pclass)
    """
    )
    fun findWithFilters(
        survived: Int?,
        pclass: Int?,
        pageable: Pageable
    ): Page<PassengerEntity>
}
