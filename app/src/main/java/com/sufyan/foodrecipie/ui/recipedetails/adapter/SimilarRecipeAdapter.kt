package com.sufyan.foodrecipie.ui.recipedetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sufyan.foodrecipie.R
import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.databinding.LayoutItemRecipeBinding
import javax.inject.Inject

class SimilarRecipeAdapter @Inject constructor() :
    RecyclerView.Adapter<SimilarRecipeAdapter.RecipeListViewHolder>() {
    var onItemClickListener: ((view: View, position: Int, data: RecipeDetailResponse.Recipe?) -> Unit)? =
        null
    private val recipeList: MutableList<RecipeDetailResponse.Recipe> = mutableListOf()
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
        holder.bind(recipeList[position], position)
    }

    override fun getItemCount() = recipeList.size

    inner class RecipeListViewHolder(private val itemBinding: LayoutItemRecipeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: RecipeDetailResponse.Recipe, position: Int) {
            with(itemBinding) {
                val recipePictureUrl = item.thumbnailUrl.takeIf {
                    it != null
                }
                if (recipePictureUrl.isNullOrBlank()) ivThumbnail.setImageResource(R.drawable.ic_play_button) else
                    Glide.with(ivThumbnail.context)
                        .load(recipePictureUrl)
                        .into(ivThumbnail)
                tvRecipeName.text = item.name

                clMain.setOnClickListener {
                    onItemClickListener?.invoke(itemView, position, item)
                }
            }
        }
    }

    fun setList(list: List<RecipeDetailResponse.Recipe>) {
        this.recipeList.clear()
        this.recipeList.addAll(list)
        notifyDataSetChanged()
    }
}
