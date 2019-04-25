package konnov.commr.vk.imageprocessor.data.source

import android.graphics.Bitmap

interface ImageLoadedCallback { //TODO replace a callback with rxjava
    fun bitmapLoaded(bitmap: Bitmap)
    fun bitmapFailed()
}