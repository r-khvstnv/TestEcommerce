package com.rkhvstnv.testecommerce.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rkhvstnv.testecommerce.core_data.domain.model.HotSale
import com.rkhvstnv.testecommerce.home.databinding.ItemHotOfferBinding
import com.rkhvstnv.testecommerce.utils.GRAVITY_CENTER_CROP
import com.rkhvstnv.testecommerce.utils.GRAVITY_CENTER_INSIDE
import com.rkhvstnv.testecommerce.utils.loadImage

internal class HotSaleAdapter(
    private val context: Context
): ListAdapter<HotSale, HotSaleAdapter.VieHolder>(HotSaleDiff()) {
    class VieHolder(val binding: ItemHotOfferBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VieHolder {
        val binding = ItemHotOfferBinding.inflate(LayoutInflater.from(context), parent, false)
        return VieHolder(binding)
    }

    override fun onBindViewHolder(holder: VieHolder, position: Int) {
        val hotSale = getItem(position)

        with(holder.binding){
            ivHotOfferImage.apply {
                loadImage(hotSale.image, this.GRAVITY_CENTER_CROP)
            }

            tvHotOfferTitle.text = hotSale.title
            tvHotOfferSubtitle.text = hotSale.subtitle

            tvBadgeNew.visibility =  if (hotSale.is_new){
                View.VISIBLE
            } else{
                View.GONE
            }
            tvHotOfferBuy.visibility =  if (hotSale.is_buy){
                View.VISIBLE
            } else{
                View.GONE
            }
        }
    }
}

private class HotSaleDiff: DiffUtil.ItemCallback<HotSale>(){
    override fun areItemsTheSame(oldItem: HotSale, newItem: HotSale): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: HotSale, newItem: HotSale): Boolean = oldItem.id == newItem.id

}
