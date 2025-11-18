package com.leonardo.titanic.enums

/**
 * Enum representing the survival status of a Titanic passenger.
 *
 * @property code Integer code corresponding to the survival status:
 *   - 0 = Did not survive
 *   - 1 = Survived
 */
enum class Survived(val code: Int) {
    NO(0),
    YES(1);

    companion object {
        /**
         * Returns the [Survived] enum corresponding to the given code.
         *
         * @param code Integer code of survival (0 or 1). Can be null.
         * @return The corresponding [Survived] enum, or null if code is null.
         * @throws IllegalArgumentException if the code is not 0 or 1.
         */
        fun fromCode(code: Int?): Survived? {
            if (code == null) return null

            require(code == 0 || code == 1) {
                "survived must be 0 or 1"
            }
            return entries.first { it.code == code }
        }
    }
}
