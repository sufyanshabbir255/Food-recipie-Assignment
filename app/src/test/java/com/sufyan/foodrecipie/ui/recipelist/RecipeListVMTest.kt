package com.sufyan.foodrecipie.ui.recipelist


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sufyan.foodrecipie.CoroutineRule
import com.sufyan.foodrecipie.data.base.NetworkResult
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.data.network.IServiceProvider
import com.sufyan.foodrecipie.data.network.RecipeRepository
import com.sufyan.foodrecipie.ui.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipeListVMTest {

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    var mainCoroutineRule = CoroutineRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `fetch recipe list with success`() = runTest {
        val from = 0
        val size = 0
        val mockRepository = mockk<RecipeRepository> {
            coEvery { fetchRecipeList(from, size) } returns NetworkResult.Success(
                mockk { coEvery { recipes } returns listOf(RecipeListResponse.Recipe()) }
            )
        }
        val sut = RecipeListVM(mockRepository)
        sut.getRecipeList(from, size)
        Assert.assertEquals(listOf(RecipeListResponse.Recipe()), sut.recipeList.getOrAwaitValue().recipes)

    }

    @Test
    fun `fetch recipe list with failure`() = runTest {
        val errorMsg = "This request unfortunately failed please try again"
        val from = 0
        val size = 0
        val mockRepository = mockk<IServiceProvider>() {
            coEvery {
                fetchRecipeList(from, size)
            } returns NetworkResult.Error(
                mockk { coEvery { message } returns errorMsg })
        }
        val sut = RecipeListVM(mockRepository)
        sut.getRecipeList(from, size)
        Assert.assertEquals(RecipeListResponse(), sut.recipeList.getOrAwaitValue())

    }
}