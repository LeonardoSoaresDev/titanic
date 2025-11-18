package com.leonardo.titanic.enums

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PassengerClassTest {

    @Test
    fun `should return FIRST when code is 1`() {
        val result = PassengerClass.fromCode(1)
        assertEquals(PassengerClass.FIRST, result)
    }

    @Test
    fun `should return SECOND when code is 2`() {
        val result = PassengerClass.fromCode(2)
        assertEquals(PassengerClass.SECOND, result)
    }

    @Test
    fun `should return THIRD when code is 3`() {
        val result = PassengerClass.fromCode(3)
        assertEquals(PassengerClass.THIRD, result)
    }

    @Test
    fun `should return null when code is null`() {
        val result = PassengerClass.fromCode(null)
        assertNull(result)
    }

    @Test
    fun `should throw exception when code is less than 1`() {
        val ex = assertThrows<IllegalArgumentException> {
            PassengerClass.fromCode(0)
        }
        assertEquals("pclass must be 1, 2, or 3", ex.message)
    }

    @Test
    fun `should throw exception when code is greater than 3`() {
        val ex = assertThrows<IllegalArgumentException> {
            PassengerClass.fromCode(4)
        }
        assertEquals("pclass must be 1, 2, or 3", ex.message)
    }
}
