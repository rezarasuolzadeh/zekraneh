package ir.rezarasoulzadeh.zekraneh.utils.enums

import androidx.annotation.ColorRes
import ir.rezarasoulzadeh.zekraneh.R

enum class ColorType(@ColorRes val color: Int) {
    WHITE(color = R.color.white),
    BLACK(color = R.color.black),
    GREEN(color = R.color.green),
    RED(color = R.color.red)
}