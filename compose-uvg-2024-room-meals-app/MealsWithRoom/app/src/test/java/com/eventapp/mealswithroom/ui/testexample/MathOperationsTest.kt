package com.eventapp.mealswithroom.ui.testexample

import com.eventapp.mealswithroom.ui.testsexample.MathOperations
import org.junit.Assert.assertEquals
import org.junit.Test

class MathOperationsTest {

    private val mathOperations = MathOperations()

    @Test
    fun `addition test`() {
        val result = mathOperations.add(5, 3)
        assertEquals("5 + 3 should equal 8",8, result)
    }

    @Test
    fun `subtraction test`() {
        val result = mathOperations.subtract(10, 4)
        assertEquals("10 - 4 should equal 6", 6, result, )
    }

    @Test
    fun `multiplication test`() {
        val result = mathOperations.multiply(7, 6)
        assertEquals("7 * 6 should equal 42",42, result, )
    }

    @Test
    fun `division test`() {
        val result = mathOperations.divide(20, 4)
        assertEquals("20 / 4 should equal 5",5, result, )
    }
}