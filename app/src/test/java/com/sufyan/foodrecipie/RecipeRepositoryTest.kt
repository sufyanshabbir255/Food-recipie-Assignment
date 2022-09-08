package com.sufyan.foodrecipie

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sufyan.foodrecipie.model.RecipeListResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

internal class RecipeRepositoryTest {

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getRecipeList() {
        val mockResponse = Response.success(readJsonFile())
        val mockService = mockk<RecipeService>()
        coEvery { mockService.getRecipeListRequest() } returns mockResponse
        val sut = RecipeRepository(mockService)
        mainCoroutineRule.runBlockingTest {
            val actualRecipeList = sut.getRecipeList()
            Assert.assertEquals(actualRecipeList.results, mockResponse.body()?.results)
        }
    }

    private fun readJsonFile(): RecipeListResponse {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<RecipeListResponse>() {}.type
        return gson.fromJson(ReadAssetFile.readFileFromTestResources("FoodRecipeResponse.json"), itemType)
    }
}
