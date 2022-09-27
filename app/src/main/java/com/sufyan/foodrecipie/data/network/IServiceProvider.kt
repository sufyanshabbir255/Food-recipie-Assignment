package com.sufyan.foodrecipie.data.network

import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.data.base.NetworkResult

interface IServiceProvider {
    suspend fun fetchRecipeList(): NetworkResult<RecipeListResponse>
    suspend fun fetchRecipeDetails(): NetworkResult<RecipeDetailResponse>
}
