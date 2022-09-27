package com.sufyan.foodrecipie.ui

import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse

sealed class ViewState {
    object Loading : ViewState()
    data class ResponseLoaded(val response: List<RecipeListResponse.Recipe>?) : ViewState()
    data class ResponseLoadFailure(val errorMessage: String) : ViewState()
}

sealed class SimilarRecipeViewState {
    object Loading : SimilarRecipeViewState()
    data class ResponseLoaded(val response: List<RecipeDetailResponse.Recipe>?) : SimilarRecipeViewState()
    data class ResponseLoadFailure(val errorMessage: String) : SimilarRecipeViewState()
}
