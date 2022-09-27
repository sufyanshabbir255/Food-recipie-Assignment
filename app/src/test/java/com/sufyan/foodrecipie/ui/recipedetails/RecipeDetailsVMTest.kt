package com.sufyan.foodrecipie.ui.recipedetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sufyan.foodrecipie.CoroutineRule
import com.sufyan.foodrecipie.data.network.IServiceProvider
import com.sufyan.foodrecipie.ReadAssetFile
import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.data.base.NetworkResult
import com.sufyan.foodrecipie.ui.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RecipeDetailsVMTest {
    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    var mainCoroutineRule = CoroutineRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `fetch recipe detail list response with success`() {
        val mockResponse = readJson()
        val mockRepository = mockk<IServiceProvider>() {
            coEvery {
                fetchRecipeDetails()
            } returns NetworkResult.Success(
                mockk {
                    coEvery { results } returns listOf(mockResponse.results!![0])
                }
            )
        }
        val sut = RecipeDetailsVM(mockRepository)
        sut.getRecipeDetails()
        Assert.assertEquals(listOf(mockResponse.results?.get(0)), sut.recipeDetails.getOrAwaitValue().results)
    }

    @Test
    fun `fetch recipe details list response with error`() {
        val errorMsg = "This request unfortunately failed please try again"
        val mockRepository = mockk<IServiceProvider> {
            coEvery { fetchRecipeDetails() } returns NetworkResult.Error(
                mockk {
                    coEvery {
                        message
                    } returns errorMsg
                }
            )
        }
        val sut = RecipeDetailsVM(mockRepository)
        sut.getRecipeDetails()

        Assert.assertEquals(RecipeDetailResponse(), sut.recipeDetails.getOrAwaitValue())

    }

    private fun readJson(): RecipeDetailResponse {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<RecipeDetailResponse>() {}.type
        return gson.fromJson(ReadAssetFile.readFileFromTestResources("FoodRecipeDetailResponse.json"), itemType)
    }
}