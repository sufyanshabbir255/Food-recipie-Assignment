package com.sufyan.foodrecipie

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sufyan.foodrecipie.model.RecipeListResponse
import com.sufyan.foodrecipie.network.base.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
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
    fun `test if network success response map with defined model`() {
        val mockResponse = Response.success(readJsonFile())
        val mockService = mockk<RecipeService>()
        coEvery { mockService.getRecipeListRequest() } returns mockResponse
        val sut = RecipeRepository(mockService)
        mainCoroutineRule.runBlockingTest {
            val actualRecipeList = sut.getRecipeList() as NetworkResult.Success
            Assert.assertEquals(mockResponse.body()?.results, actualRecipeList.data.results)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test if network response fails and test the error msg mapping`() {
        val errorMsg = "This api is not working"
        val mockResponse = Response.error<RecipeListResponse>(
            400,
            errorMsg.toResponseBody("application/json".toMediaTypeOrNull())
        )
        val mockService = mockk<RecipeService>()
        coEvery { mockService.getRecipeListRequest() } returns mockResponse
        val sut = RecipeRepository(mockService)
        mainCoroutineRule.runBlockingTest {
            val expectedResponse = sut.getRecipeList() as NetworkResult.Error
            Assert.assertEquals(errorMsg, expectedResponse.error)
        }

    }

    private fun readJsonFile(): RecipeListResponse {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<RecipeListResponse>() {}.type
        return gson.fromJson(ReadAssetFile.readFileFromTestResources("FoodRecipeResponse.json"), itemType)
    }
}
