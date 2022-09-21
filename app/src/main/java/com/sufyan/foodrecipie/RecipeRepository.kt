package com.sufyan.foodrecipie

import com.sufyan.foodrecipie.model.RecipeListResponse
import com.sufyan.foodrecipie.network.base.NetworkResult
import retrofit2.Response

class RecipeRepository constructor(private val service: RecipeService) : IServiceProvider {
    override suspend fun getRecipeList(): NetworkResult<RecipeListResponse> {
        val response = service.getRecipeListRequest()
        return handleResponse(response)
    }


    private fun <T : Any> handleResponse(response: Response<T>): NetworkResult<T> {
        return try {
            return if (response.isSuccessful)
                NetworkResult.Success(response.body()!!)
            else
                NetworkResult.Error("This api is not working")
        } catch (e: Exception) {
            NetworkResult.Error(e.localizedMessage ?: "")
        }
    }
}