package com.sufyan.foodrecipie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sufyan.foodrecipie.R
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.databinding.LayoutItemRecipeBinding

class RecipeListViewHolder(private val itemBinding: LayoutItemRecipeBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(
        item: RecipeListResponse.Recipe,
        position: Int,
        onItemClickListener: ((view: View, position: Int, data: RecipeListResponse.Recipe?) -> Unit)?
    ) {
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