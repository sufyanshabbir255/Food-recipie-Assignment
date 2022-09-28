package com.sufyan.foodrecipie.data.network

import com.sufyan.foodrecipie.data.base.NetworkResult
import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val service: RecipeService) : IServiceProvider {

    override suspend fun fetchRecipeList(from: Int, size: Int): NetworkResult<RecipeListResponse> {
        val response = service.getRecipeListRequest(from = from, size = size)
        return handleResponse(response)
    }

    override suspend fun fetchRecipeDetails(recipeId: Int): NetworkResult<RecipeDetailResponse> {
        return handleResponse(service.getRecipeDetailsRequest(recipeId))
    }

    private fun <T : Any> handleResponse(response: Response<T>): NetworkResult<T> {
        return try {
            return if (response.isSuccessful)
                NetworkResult.Success(response.body()!!)
            else
                NetworkResult.Error(IOException("This api is not working"))
        } catch (exception: Exception) {
            NetworkResult.Error(exception)
        }
    }
}