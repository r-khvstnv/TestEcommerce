package com.rkhvstnv.testecommerce.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rkhvstnv.testecommerce.details.databinding.ItemProductPreviewBinding
import com.rkhvstnv.testecommerce.utils.GRAVITY_CENTER_INSIDE
import com.rkhvstnv.testecommerce.utils.loadImage

internal class ProductImageAdapter(
    private val context: Context
): ListAdapter<String, ProductImageAdapter.ViewHolder>(ProductImageDiff()) {
    class ViewHolder(val binding: ItemProductPreviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductPreviewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath = getItem(position)
        holder.binding.itemProductImage.apply {
            loadImage(imagePath, this.GRAVITY_CENTER_INSIDE)
        }
    }

}

private class ProductImageDiff: DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.contentEquals(newItem)
    }
}