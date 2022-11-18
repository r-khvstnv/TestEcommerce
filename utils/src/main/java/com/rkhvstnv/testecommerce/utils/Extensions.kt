package com.rkhvstnv.testecommerce.utils

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import java.text.DecimalFormat


/**
 * Method for loading images using it's [path] into [ImageView].
 *
 * Possible [gravity] parameters are [GRAVITY_CENTER_CROP], [GRAVITY_CENTER_INSIDE] and [GRAVITY_FIT_CENTER].
 * Otherwise will be used [GRAVITY_FIT_CENTER].
 * */
fun ImageView.loadImage(path: String, gravity: Int){
    Glide.with(context).load(path).apply {
        when(gravity){
            GRAVITY_CENTER_INSIDE -> centerInside().into(this@loadImage)
            GRAVITY_CENTER_CROP -> centerCrop().into(this@loadImage)
            GRAVITY_FIT_CENTER -> fitCenter().into(this@loadImage)
            else -> fitCenter().into(this@loadImage)
        }
    }
}

/**
 * Method for loading drawable resource into [ImageView].
 * By default is used centerInside gravity.
 * */
fun ImageView.loadImage(@DrawableRes drawable: Int){
    Glide.with(context).load(drawable).centerInside().into(this)
}

/**
 * Flag for setting the parameter - gravity. Applicable only for [ImageView.loadImage] method.
 * */
val ImageView.GRAVITY_CENTER_INSIDE: Int
    get() = 0
/**
 * Flag for setting the parameter - gravity. Applicable only for [ImageView.loadImage] method.
 * */
val ImageView.GRAVITY_CENTER_CROP: Int
    get() = 1
/**
 * Flag for setting the parameter - gravity. Applicable only for [ImageView.loadImage] method.
 * */
val ImageView.GRAVITY_FIT_CENTER: Int
    get() = 2

/**
 * Method for formatting [Number] into [String] by pattern #,###.00
 * */
fun Number.formatToTwoDecimals(): String = DecimalFormat("#,###.00").format(this.toDouble())
/**
 * Method for formatting [Number] into [String] by pattern #,###
 * */
fun Number.format(): String = DecimalFormat("#,###").format(this.toDouble())

/**
 * Method sets [View]'s tint color from [hex].
 * */
fun View.setBackgroundTintColor(hex: String){
    val color = Color.parseColor(hex)
    background.setTint(color)
}

/**
 * Extension method converts theme's [attrColor] to @[ColorInt].
 * */
@ColorInt
fun Context.getAttrColor(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRef: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRef)
    return typedValue.data
}