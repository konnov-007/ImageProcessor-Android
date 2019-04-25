package konnov.commr.vk.imageprocessor.util

import androidx.annotation.StringRes
import konnov.commr.vk.imageprocessor.domain.model.Image

sealed class ResultImageState

class ResultImageStateEmpty(@StringRes val message : Int) : ResultImageState()

class ResultImageStateSuccess(val resultImages : ArrayList<Image>) : ResultImageState()