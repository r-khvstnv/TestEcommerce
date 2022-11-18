package com.rkhvstnv.testecommerce.home.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.View
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.rkhvstnv.testecommerce.core_data.domain.models.DisplayableItem
import com.rkhvstnv.testecommerce.core_data.domain.models.BestSeller
import com.rkhvstnv.testecommerce.core_data.domain.models.HotSale
import com.rkhvstnv.testecommerce.home.databinding.ItemHotOfferBinding
import com.rkhvstnv.testecommerce.home.databinding.ItemProductBinding
import com.rkhvstnv.testecommerce.utils.*
import com.rkhvstnv.testecommerce.utils.R

internal class HomeAdapterDelegator(
    itemCallBack: (Int) -> Unit
): ListDelegationAdapter<List<DisplayableItem>>(
    adapterDelegateBestSeller(itemCallBack = itemCallBack),
    adapterDelegateHotSale()
)


@SuppressLint("SetTextI18n")
private fun adapterDelegateBestSeller(itemCallBack: (Int) -> Unit) =
    adapterDelegateViewBinding<BestSeller, DisplayableItem, ItemProductBinding>(
    {layoutInflater, parent -> ItemProductBinding.inflate(layoutInflater, parent, false) }
){

    binding.root.setOnClickListener { itemCallBack(item.id) }

    bind {

        with(binding){
            ivProductImage.apply {
                loadImage(item.image, this.GRAVITY_CENTER_INSIDE)
            }

            tvProductCost.text = getString(R.string.currency_us) + item.discount_price.format()
            tvProductOldCost.apply {
                text = getString(R.string.currency_us) + item.price_without_discount.format()
                paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            tvProductName.text = item.title

            if (item.is_favorites){
                ivProductFavourite.loadImage(com.rkhvstnv.testecommerce.core.R.drawable.ic_favourite_solid)
            } else{
                ivProductFavourite.loadImage(com.rkhvstnv.testecommerce.core.R.drawable.ic_favourite)
            }
        }
    }
}

private fun adapterDelegateHotSale() = adapterDelegateViewBinding<HotSale, DisplayableItem, ItemHotOfferBinding>(
    {layoutInflater, parent -> ItemHotOfferBinding.inflate(layoutInflater, parent, false) }
){

    bind {

        with(binding){
            tvHotOfferTitle.text = item.title
            tvHotOfferSubtitle.text = item.subtitle
            ivHotOfferImage.apply {
                loadImage(item.image, this.GRAVITY_CENTER_CROP)
            }

            tvBadgeNew.visibility =  if (item.is_new){
                View.VISIBLE
            } else{
                View.GONE
            }
            tvHotOfferBuy.visibility =  if (item.is_buy){
                View.VISIBLE
            } else{
                View.GONE
            }
        }
    }
}