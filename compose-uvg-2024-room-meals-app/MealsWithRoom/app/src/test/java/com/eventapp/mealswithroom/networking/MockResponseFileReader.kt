package com.eventapp.mealswithroom.networking

import java.io.InputStreamReader

class MockResponseFileReader(path: String) {
    val content: String

    init {
        val inputStream = this.javaClass.classLoader?.getResourceAsStream(path)
        content = inputStream?.let {
            InputStreamReader(it).readText()
        } ?: throw IllegalArgumentException("File not found at $path")
    }
}