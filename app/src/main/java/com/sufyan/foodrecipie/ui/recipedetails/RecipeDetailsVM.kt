package com.sufyan.foodrecipie.ui.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sufyan.foodrecipie.model.RecipeDetailResponse
import com.sufyan.foodrecipie.network.IServiceProvider
import com.sufyan.foodrecipie.network.base.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeDetailsVM(var recipeService: IServiceProvider) : ViewModel(), IRecipeDetails {
    private var _recipeDetails: MutableLiveData<RecipeDetailResponse> = MutableLiveData()
    override val recipeDetails: LiveData<RecipeDetailResponse> = _recipeDetails

    override fun getRecipeDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = recipeService.fetchRecipeDetails()) {
                is NetworkResult.Success -> {
                    _recipeDetails.postValue(response.data)
                }
                is NetworkResult.Error -> {
                    _recipeDetails.postValue(RecipeDetailResponse())
                }
            }
        }
    }
}