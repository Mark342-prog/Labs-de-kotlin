package com.eventapp.mealswithroom.ui.testexample

import com.eventapp.mealswithroom.ui.testsexample.BadMathOperations
import org.junit.Test

class BadMathOperationsTest {

    private val badMathOperations = BadMathOperations()

    @Test
    fun `addRandomToNumber test - unreliable due to randomness`() {
        val result = badMathOperations.addRandomToNumber(5)
        // Cannot assert specific values due to randomness in fetchRandomNumber()
    }

    @Test
    fun `multiplyWithCurrentSecond test - inconsistent due to system time`() {
        val result = badMathOperations.multiplyWithCurrentSecond(5)
        // Test result is unpredictable as it depends on the current second
    }
}