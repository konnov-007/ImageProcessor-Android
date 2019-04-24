package konnov.commr.vk.imageprocessor.util

import android.graphics.drawable.Drawable

fun imageIsSet(image: Drawable?): Boolean {
    if(image == null) {
        return false
    }
    return true
}