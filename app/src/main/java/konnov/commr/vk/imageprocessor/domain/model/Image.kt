package konnov.commr.vk.imageprocessor.domain.model

import android.graphics.Bitmap
import java.util.*

data class Image (val bitmap: Bitmap, val id: String = UUID.randomUUID().toString())