package ir.rezarasuolzadeh.zekraneh.utils.extensions

/**
 * check the integer value if is null return 0 else return it's own value.
 */
fun Int?.orZero(): Int = this ?: 0