package com.sufyan.foodrecipie

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sufyan.foodrecipie.model.RecipeDetailResponse
import com.sufyan.foodrecipie.model.RecipeListResponse
import com.sufyan.foodrecipie.network.RecipeRepository
import com.sufyan.foodrecipie.network.RecipeService
import com.sufyan.foodrecipie.network.base.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RecipeRepositoryTest {

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    var mainCoroutineRule = CoroutineRule()

    private var mockService = mockk<RecipeService>()
    private val sut = RecipeRepository(mockService)

    @Test
    fun `test if recipe list success response map with defined model`() = runTest {
        val mockResponse = Response.success(readJsonFile())
        val mockService = mockk<RecipeService>()
        coEvery { mockService.getRecipeListRequest() } returns mockResponse
        val sut = RecipeRepository(mockService)
        val actualRecipeList = sut.fetchRecipeList() as NetworkResult.Success
        Assert.assertEquals(mockResponse.body()?.recipes, actualRecipeList.data.recipes)
    }

    @Test
    fun `test if network response fails and test the error msg mapping`() = runTest {
        val errorMsg = "This api is not working"
        val mockResponse = Response.error<RecipeListResponse>(
            400,
            errorMsg.toResponseBody("application/json".toMediaTypeOrNull())
        )
        val mockService = mockk<RecipeService>()
        coEvery { mockService.getRecipeListRequest() } returns mockResponse
        val sut = RecipeRepository(mockService)
        val expectedResponse = sut.fetchRecipeList() as NetworkResult.Error
        Assert.assertEquals(errorMsg, expectedResponse.error.message)
    }

    @Test
    fun `test if recipe detail success response map on the defined model`() = runTest {
        val mockResponse = Response.success(readJsonFile1())
        coEvery {
            mockService.getRecipeDetailsRequest()
        } returns mockResponse
        val expectedResponse = sut.fetchRecipeDetails() as NetworkResult.Success
        Assert.assertEquals(mockResponse.body()?.results, expectedResponse.data.results)
    }

    @Test
    fun `test if recipe detail failed response map on the error`() = runTest {
        val error = "This api is not working"
        val errorResponse = Response.error<RecipeDetailResponse>(
            400,
            error.toResponseBody("application/json".toMediaTypeOrNull())
        )
        coEvery {
            mockService.getRecipeDetailsRequest()
        } returns errorResponse

        val expectedResponse = sut.fetchRecipeDetails() as NetworkResult.Error
        Assert.assertEquals(error, expectedResponse.error.message)
    }

    private fun readJsonFile(): RecipeListResponse {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<RecipeListResponse>() {}.type
        return gson.fromJson(ReadAssetFile.readFileFromTestResources("FoodRecipeResponse.json"), itemType)
    }

    private fun readJsonFile1(): RecipeDetailResponse {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<RecipeDetailResponse>() {}.type
        return gson.fromJson(ReadAssetFile.readFileFromTestResources("FoodRecipeDetailResponse.json"), itemType)
    }

//    inline fun <reified T : Any> getApiMockResponse(
//        fileName: String,
//        noinline completionHandler: ((data: T) -> Unit)? = null
//    ) {
//        val gson = GsonBuilder().create()
//        val itemType = object : TypeToken<T>() {}.type
//        completionHandler?.let {
//            it.invoke(gson.fromJson(ReadAssetFile.readFileFromTestResources(fileName), itemType))
//        }
//    }
}
