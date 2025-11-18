package com.leonardo.titanic.enums

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SurvivedTest {

    @Test
    fun `should return NO when code is 0`() {
        val result = Survived.fromCode(0)
        assertEquals(Survived.NO, result)
    }

    @Test
    fun `should return YES when code is 1`() {
        val result = Survived.fromCode(1)
        assertEquals(Survived.YES, result)
    }

    @Test
    fun `should return null when code is null`() {
        val result = Survived.fromCode(null)
        assertNull(result)
    }

    @Test
    fun `should throw exception when code is negative`() {
        val ex = assertThrows<IllegalArgumentException> {
            Survived.fromCode(-1)
        }
        assertEquals("survived must be 0 or 1", ex.message)
    }

    @Test
    fun `should throw exception when code is any invalid number`() {
        val ex = assertThrows<IllegalArgumentException> {
            Survived.fromCode(999)
        }
        assertEquals("survived must be 0 or 1", ex.message)
    }
}
