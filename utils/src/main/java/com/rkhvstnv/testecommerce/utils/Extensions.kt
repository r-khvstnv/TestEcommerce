package com.rkhvstnv.testecommerce.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.text.DecimalFormat

fun ImageView.loadImage(path: String){
    Glide.with(context).load(path).centerCrop().into(this)
}
fun ImageView.loadImage(@DrawableRes drawable: Int){
    Glide.with(context).load(drawable).into(this)
}

fun Number.formatToTwoDecimals() = DecimalFormat("#,###.00").format(this.toDouble())
fun Number.format() = DecimalFormat("#,###").format(this.toDouble())

fun View.setBackgroundTintColor(hex: String){
    val color = Color.parseColor(hex)
    background.setTint(color)
}