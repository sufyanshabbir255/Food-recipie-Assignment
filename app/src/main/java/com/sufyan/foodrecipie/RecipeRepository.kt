package com.sufyan.foodrecipie

import com.sufyan.foodrecipie.model.RecipeListResponse

class RecipeRepository constructor(private val service: RecipeService) : IServiceProvider {
    override suspend fun getRecipeList(): RecipeListResponse {
        val response = service.getRecipeListRequest()
        if (response.isSuccessful)
            return response.body()?.let {
                return@let it
            } ?: RecipeListResponse()
        else
            return RecipeListResponse()
    }
}