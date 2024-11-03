package ir.rezarasuolzadeh.zekraneh.utils.enums

import androidx.annotation.ColorRes
import ir.rezarasuolzadeh.zekraneh.R

enum class ColorType(@ColorRes val color: Int) {
    WHITE(color = R.color.white),
    BLACK(color = R.color.black),
    GREEN(color = R.color.lightGreen),
    RED(color = R.color.red)
}