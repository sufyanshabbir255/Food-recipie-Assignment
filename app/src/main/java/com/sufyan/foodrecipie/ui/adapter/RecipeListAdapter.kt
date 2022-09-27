package com.sufyan.foodrecipie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.databinding.LayoutItemRecipeBinding
import javax.inject.Inject

class RecipeListAdapter @Inject constructor() : RecyclerView.Adapter<RecipeListViewHolder>() {
    private val recipeList: MutableList<RecipeListResponse.Recipe> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        return RecipeListViewHolder(
            LayoutItemRecipeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount() = recipeList.size

    fun setList(list: List<RecipeListResponse.Recipe>?) {
        this.recipeList.clear()
        this.recipeList.addAll(list ?: listOf())
        notifyDataSetChanged()
    }
}
