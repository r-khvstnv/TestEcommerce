package com.rkhvstnv.testecommerce.utils

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.text.DecimalFormat


fun ImageView.loadImage(path: String, gravity: Int){
    Glide.with(context).load(path).apply {
        when(gravity){
            GRAVITY_CENTER_INSIDE -> centerInside().into(this@loadImage)
            GRAVITY_CENTER_CROP -> centerCrop().into(this@loadImage)
            FIT_CENTER -> fitCenter().into(this@loadImage)
            else -> fitCenter().into(this@loadImage)
        }
    }
}
fun ImageView.loadImage(@DrawableRes drawable: Int){
    Glide.with(context).load(drawable).centerInside().into(this)
}

val ImageView.GRAVITY_CENTER_INSIDE: Int
    get() = 0
val ImageView.GRAVITY_CENTER_CROP: Int
    get() = 1
val ImageView.FIT_CENTER: Int
    get() = 2

fun Number.formatToTwoDecimals(): String = DecimalFormat("#,###.00").format(this.toDouble())
fun Number.format(): String = DecimalFormat("#,###").format(this.toDouble())

fun View.setBackgroundTintColor(hex: String){
    val color = Color.parseColor(hex)
    background.setTint(color)
}

@ColorInt
fun Context.getAttrColor(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRef: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRef)
    return typedValue.data
}