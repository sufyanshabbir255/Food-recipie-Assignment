package com.sufyan.foodrecipie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.databinding.LayoutItemRecipeBinding
import javax.inject.Inject

class RecipeListAdapter @Inject constructor() : RecyclerView.Adapter<RecipeListViewHolder>() {
    var onItemClickListener: ((view: View, position: Int, data: RecipeListResponse.Recipe?) -> Unit)? =
        null
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
        holder.bind(recipeList[position], position, onItemClickListener)
    }

    override fun getItemCount() = recipeList.size

    fun setList(list: List<RecipeListResponse.Recipe>?) {
        this.recipeList.clear()
        this.recipeList.addAll(list ?: listOf())
        notifyDataSetChanged()
    }

//    inner class RecipeListViewHolder(private val itemBinding: LayoutItemRecipeBinding) :
//        RecyclerView.ViewHolder(itemBinding.root) {
//
//        fun bind(
//            item: RecipeListResponse.Recipe,
//            position: Int,
//            onItemClickListener: ((view: View, position: Int, data: RecipeListResponse.Recipe?) -> Unit)?
//        ) {
//            with(itemBinding) {
//                val recipePictureUrl = item.thumbnailUrl.takeIf {
//                    it != null
//                }
//                if (recipePictureUrl.isNullOrBlank()) ivThumbnail.setImageResource(R.drawable.ic_play_button) else
//                    Glide.with(ivThumbnail.context)
//                        .load(recipePictureUrl)
//                        .into(ivThumbnail)
//                tvRecipeName.text = item.name
//
//                clMain.setOnClickListener {
//                    onItemClickListener?.invoke(itemView, position, item)
//                }
//            }
//        }
//    }
}

