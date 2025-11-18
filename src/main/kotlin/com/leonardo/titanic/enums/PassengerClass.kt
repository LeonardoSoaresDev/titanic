package com.leonardo.titanic.enums

/**
 * Enum representing the class of a Titanic passenger.
 *
 * @property code Integer code corresponding to the passenger class:
 *   - 1 = FIRST class
 *   - 2 = SECOND class
 *   - 3 = THIRD class
 */
enum class PassengerClass(val code: Int) {
    FIRST(1),
    SECOND(2),
    THIRD(3);

    companion object {
        /**
         * Returns the [PassengerClass] corresponding to the given code.
         *
         * @param code Integer code of the class (1, 2, or 3). Can be null.
         * @return The corresponding [PassengerClass], or null if code is null.
         * @throws IllegalArgumentException if the code is not between 1 and 3.
         */
        fun fromCode(code: Int?): PassengerClass? {
            if (code == null) return null

            require(code in 1..3) {
                "pclass must be 1, 2, or 3"
            }
            return entries.first { it.code == code }
        }
    }
}
