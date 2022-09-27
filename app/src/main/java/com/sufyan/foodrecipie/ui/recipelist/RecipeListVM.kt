package com.sufyan.foodrecipie.ui.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.data.network.IServiceProvider
import com.sufyan.foodrecipie.data.base.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListVM @Inject constructor(var recipeServiceProvider: IServiceProvider) : ViewModel(), IRecipeList {
    private var _recipeList: MutableLiveData<RecipeListResponse> = MutableLiveData()
    override val recipeList: LiveData<RecipeListResponse> = _recipeList

    override fun getRecipeList() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = recipeServiceProvider.fetchRecipeList()) {
                is NetworkResult.Success -> {
                    _recipeList.postValue(response.data)
                }
                is NetworkResult.Error -> {
                    _recipeList.postValue(RecipeListResponse())
                }
            }
        }
    }
}