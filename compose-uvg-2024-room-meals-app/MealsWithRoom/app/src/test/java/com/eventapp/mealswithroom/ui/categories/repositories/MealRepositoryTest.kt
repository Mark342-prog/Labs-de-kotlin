package com.eventapp.mealswithroom.ui.categories.repositories

import com.eventapp.mealswithroom.database.categories.MealCategoryDao
import com.eventapp.mealswithroom.networking.response.categories.Categories
import com.eventapp.mealswithroom.networking.response.categories.MealsCategoriesResponse
import com.eventapp.mealswithroom.networking.response.categories.toEntity
import com.eventapp.mealswithroom.networking.webservices.IMealsWebService
import com.eventapp.mealswithroom.networking.webservices.MealsWebService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MealsCategoryRepositoryTest {

    @Mock
    private lateinit var webService: IMealsWebService

    @Mock
    private lateinit var mealCategoryDao: MealCategoryDao

    @InjectMocks
    private lateinit var repository: MealsCategoryRepository

    @Before
    fun setUp() {
        repository = MealsCategoryRepository(webService, mealCategoryDao)
    }

    @Test
    fun `verify getMealsCategories fetches and stores categories successfully`() = runBlocking {
        // Given
        // Arrange: Create mock response data for MealsWebService
        val mockCategories = listOf(
            Categories("1", "Beef", "Beef dishes", "https://example.com/beef.png"),
            Categories("2","Chicken", "Chicken dishes", "https://example.com/chicken.png")
        )

        // Mock MealsWebService to return the mock response
        val mockResponse = MealsCategoriesResponse(mockCategories)
        `when`(webService.getMealsCategories()).thenReturn(mockResponse)

        val mockEntities = mockCategories.map { it.toEntity() }
        `when`(mealCategoryDao.getAllMealCategories()).thenReturn(mockEntities)

        // When
        // Act: Call the repository method
        val result = repository.getMealsCategories()

        // Then
        assertNotNull(result)
        assertEquals(mockEntities, result)
        assertEquals("Beef", result[0].name)
        assertEquals("Chicken", result[1].name)
    }
}