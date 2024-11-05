package com.eventapp.mealswithroom

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.eventapp.mealswithroom.ui.testui.MyComposableScreen
import org.junit.Rule
import org.junit.Test

class MyComposableScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testButtonClickUpdatesText() {
        // Set the content with the composable under test
        composeTestRule.setContent {
            MyComposableScreen()
        }

        // Assert that initial text is displayed
        composeTestRule.onNodeWithText("Hello, World!")
            .assertIsDisplayed()

        // Perform click action on the button
        composeTestRule.onNodeWithText("Click Me")
            .performClick()

        // Assert that the text has changed after clicking the button
        composeTestRule.onNodeWithText("Hello, Compose!")
            .assertIsDisplayed()
    }
}