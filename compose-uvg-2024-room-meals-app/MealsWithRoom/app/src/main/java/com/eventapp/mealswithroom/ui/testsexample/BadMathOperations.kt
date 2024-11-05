package com.eventapp.mealswithroom.ui.testsexample

class BadMathOperations {

    private val logger = Logger() // Dependency that is hard to mock

    // A method that uses external input, making testing unpredictable
    fun addRandomToNumber(a: Int): Int {
        val randomValue = fetchRandomNumber() // External dependency, not mockable
        val result = a + randomValue
        logger.log("Adding $a and $randomValue: Result = $result") // Logs instead of returning
        return result
    }

    // Method that relies on system time, making results inconsistent
    fun multiplyWithCurrentSecond(a: Int): Int {
        val seconds = System.currentTimeMillis() / 1000 % 60 // External dependency
        val result = a * seconds.toInt()
        logger.log("Multiplying $a with $seconds: Result = $result") // Logs instead of returning
        return result
    }

    // Private method makes it difficult to test directly
    private fun fetchRandomNumber(): Int {
        return (1..100).random() // External dependency, hard to control in tests
    }
}

class Logger {
    fun log(message: String) {
        println(message) // External output, hard to verify in tests
    }
}