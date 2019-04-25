package konnov.commr.vk.imageprocessor.util

import android.graphics.Bitmap
import androidx.annotation.StringRes

sealed class SourceImageState

class ImageStateEmpty(@StringRes val message : Int) : SourceImageState()

class ImageStateSuccess(val resultBitmap : Bitmap) : SourceImageState()