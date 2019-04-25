package konnov.commr.vk.imageprocessor.screen.activity

import android.graphics.Bitmap

interface AdapterItemListener {
    fun onItemClick(itemPosition: Int, bitmap: Bitmap)
}