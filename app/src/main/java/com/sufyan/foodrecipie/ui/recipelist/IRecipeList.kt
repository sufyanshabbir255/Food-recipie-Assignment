package com.sufyan.foodrecipie.ui.recipelist

import androidx.lifecycle.LiveData
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.ui.ViewState

interface IRecipeList {
    val recipeList: LiveData<RecipeListResponse>
    val viewState: LiveData<ViewState>
    fun getRecipeList()
}
