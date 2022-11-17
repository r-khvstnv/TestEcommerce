package com.rkhvstnv.testecommerce.cart

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rkhvstnv.testecommerce.cart.databinding.ItemInCartBinding
import com.rkhvstnv.testecommerce.core_data.domain.model.ProductInCart
import com.rkhvstnv.testecommerce.utils.formatToTwoDecimals
import com.rkhvstnv.testecommerce.utils.loadImage

internal class ProductsInCartAdapter(
    private val context: Context,
    private val productAdapterCallback: ProductAdapterCallback
): ListAdapter<ProductInCart, ProductsInCartAdapter.ViewHolder>(ProductsInCartDiff()){
    class ViewHolder(val binding: ItemInCartBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInCartBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding = binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productInCart = getItem(position)

        with(holder.binding){
            ivItemInCartProductImage.loadImage(productInCart.image)
            tvItemInCartProductName.text = productInCart.name
            tvItemInCartProductCost.text =
                context.getString(com.rkhvstnv.testecommerce.utils.R.string.currency_us) +
                        productInCart.totalCost.formatToTwoDecimals()
            tvItemInCartAmount.text = productInCart.amount.toString()

            ivItemInCartDelete.setOnClickListener {
                productAdapterCallback.onDeleteClick(productInCart)
            }
            ivItemInCartIncrease.setOnClickListener {
                productAdapterCallback.onAmountChange(productInCart, newValue = productInCart.amount + 1)
            }
            ivItemInCartDecrease.setOnClickListener {
                productAdapterCallback.onAmountChange(productInCart, newValue = productInCart.amount - 1)
            }
        }
    }
}


private class ProductsInCartDiff: DiffUtil.ItemCallback<ProductInCart>(){
    override fun areItemsTheSame(oldItem: ProductInCart, newItem: ProductInCart): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: ProductInCart, newItem: ProductInCart): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.price == newItem.price &&
                oldItem.amount - newItem.amount == 0 &&
                oldItem.totalCost - newItem.totalCost == 0
    }

}