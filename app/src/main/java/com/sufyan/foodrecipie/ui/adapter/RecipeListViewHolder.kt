package com.sufyan.foodrecipie.ui.adapter

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sufyan.foodrecipie.R
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.databinding.LayoutItemRecipeBinding

class RecipeListViewHolder(private val itemBinding: LayoutItemRecipeBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    init {
        itemView.setOnClickListener {
            Toast.makeText(itemBinding.root.context, "", Toast.LENGTH_SHORT).show()
        }
    }

    fun bind(item: RecipeListResponse.Recipe) {
        with(itemBinding) {
            val recipePictureUrl = item.thumbnailUrl.takeIf {
                it != null
            }
            if (recipePictureUrl.isNullOrBlank()) ivThumbnail.setImageResource(R.drawable.ic_play_button) else
                Glide.with(ivThumbnail.context)
                    .load(recipePictureUrl)
                    .into(ivThumbnail)
            tvRecipeName.text = item.name
        }
    }
}