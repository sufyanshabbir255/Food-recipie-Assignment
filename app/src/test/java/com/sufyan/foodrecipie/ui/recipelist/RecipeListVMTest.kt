package com.sufyan.foodrecipie.ui.recipelist


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sufyan.foodrecipie.CoroutineRule
import com.sufyan.foodrecipie.IServiceProvider
import com.sufyan.foodrecipie.model.RecipeListResponse
import com.sufyan.foodrecipie.network.base.NetworkResult
import com.sufyan.foodrecipie.ui.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RecipeListVMTest {

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    var mainCoroutineRule = CoroutineRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetch recipe list with success`() = runTest {
        val mockRepository = mockk<IServiceProvider> {
            coEvery { getRecipeList() } returns NetworkResult.Success(
                mockk { coEvery { recipes } returns listOf(RecipeListResponse.Recipe()) }
            )
        }
        val sut = RecipeListVM(mockRepository)
        sut.fetchRecipeList()

        Assert.assertEquals(listOf(RecipeListResponse.Recipe()), sut.recipeList.getOrAwaitValue().recipes)

//        coVerify { mockRepository.getRecipeList() }
    }
}