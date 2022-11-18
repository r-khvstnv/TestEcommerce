package com.rkhvstnv.testecommerce.home.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rkhvstnv.testecommerce.core_data.domain.models.Category
import com.rkhvstnv.testecommerce.home.databinding.ItemCategoryBinding
import com.rkhvstnv.testecommerce.utils.getAttrColor
import com.rkhvstnv.testecommerce.utils.loadImage

internal class CategoryAdapter(
    private val context: Context,
    private val itemCategoryClicked: (Int) -> Unit
): ListAdapter<Category, CategoryAdapter.ViewHolder>(CategoryDiff()) {
    class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)

        with(holder.binding){
            ivItemCategoryImage.loadImage(category.image)
            tvItemCategoryName.text = category.name

            if (category.isSelected){
                ivItemCategoryImage.background.setTint(context.getAttrColor(com.google.android.material.R.attr.colorSecondary))
                ivItemCategoryImage.setColorFilter(context.getAttrColor(com.google.android.material.R.attr.colorOnSecondary))
                tvItemCategoryName.setTextColor(context.getAttrColor(com.google.android.material.R.attr.colorSecondary))
            } else{
                ivItemCategoryImage.background.setTint(context.getAttrColor(com.google.android.material.R.attr.colorSurface))
                ivItemCategoryImage.setColorFilter(context.getAttrColor(com.google.android.material.R.attr.colorOnSurface))
                tvItemCategoryName.setTextColor(context.getAttrColor(com.google.android.material.R.attr.colorPrimary))
            }
        }

        holder.itemView.setOnClickListener { itemCategoryClicked(category.id) }
    }
}

private class CategoryDiff: DiffUtil.ItemCallback<Category>(){
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id && oldItem.isSelected == newItem.isSelected
    }

}