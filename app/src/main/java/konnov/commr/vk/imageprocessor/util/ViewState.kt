package konnov.commr.vk.imageprocessor.util

import android.graphics.Bitmap
import androidx.annotation.StringRes

sealed class ViewState

class ViewStateEmpty(@StringRes val message : Int) : ViewState()

class ViewStateSuccess(val resultBitmap : Bitmap) : ViewState()