package konnov.commr.vk.imageprocessor.util

import android.graphics.drawable.Drawable
import android.webkit.URLUtil

fun imageIsSet(image: Drawable?): Boolean {
    if(image == null) {
        return false
    }
    return true
}

/**
 * @return true if the url is valid otherwise returns false
 */
fun isUrlValid(url: String) = URLUtil.isValidUrl(url);