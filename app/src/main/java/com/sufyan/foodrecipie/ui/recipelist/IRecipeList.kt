package com.sufyan.foodrecipie.ui.recipelist

import androidx.lifecycle.LiveData
import com.sufyan.foodrecipie.model.RecipeListResponse

interface IRecipeList {
    val recipeList: LiveData<RecipeListResponse>
    fun fetchRecipeList()
}
