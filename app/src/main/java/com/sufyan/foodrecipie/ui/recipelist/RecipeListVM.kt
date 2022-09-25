package com.sufyan.foodrecipie.ui.recipelist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sufyan.foodrecipie.IServiceProvider
import com.sufyan.foodrecipie.model.RecipeListResponse
import com.sufyan.foodrecipie.network.base.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListVM(var recipeServiceProvider: IServiceProvider) : AndroidViewModel(Application()), IRecipeList {
    private var _recipeList: MutableLiveData<RecipeListResponse> = MutableLiveData()
    override val recipeList: LiveData<RecipeListResponse> = _recipeList

    override fun fetchRecipeList() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = recipeServiceProvider.getRecipeList()) {
                is NetworkResult.Success -> {
                    _recipeList.value = response.data
                }
                is NetworkResult.Error -> {

                }
            }
        }
    }
}