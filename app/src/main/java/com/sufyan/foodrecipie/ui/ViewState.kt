package com.sufyan.foodrecipie.ui

import com.sufyan.foodrecipie.data.dtos.RecipeListResponse

sealed class ViewState {
    object Loading : ViewState()
    data class ResponseLoaded(val response: List<RecipeListResponse.Recipe>?) : ViewState()
    data class ResponseLoadFailure(val errorMessage: String) : ViewState()
}
