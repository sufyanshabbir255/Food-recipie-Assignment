package com.sufyan.foodrecipie

import com.sufyan.foodrecipie.model.RecipeDetailResponse
import com.sufyan.foodrecipie.model.RecipeListResponse
import com.sufyan.foodrecipie.network.base.NetworkResult
import retrofit2.Response
import java.io.IOException

class RecipeRepository constructor(private val service: RecipeService) : IServiceProvider {

    override suspend fun fetchRecipeList(): NetworkResult<RecipeListResponse> {
        val response = service.getRecipeListRequest()
        return handleResponse(response)
    }

    override suspend fun fetchRecipeDetails(): NetworkResult<RecipeDetailResponse> {
        return handleResponse(service.getRecipeDetailsRequest())
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