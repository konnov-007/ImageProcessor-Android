package konnov.commr.vk.imageprocessor.screen

import android.graphics.Bitmap

interface AdapterItemListener {
    fun onItemClick(itemPosition: Int, bitmap: Bitmap)
}