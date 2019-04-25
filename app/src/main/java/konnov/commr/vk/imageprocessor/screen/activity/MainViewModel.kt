package konnov.commr.vk.imageprocessor.screen.activity

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.imageprocessor.UseCase
import konnov.commr.vk.imageprocessor.UseCaseHandler
import konnov.commr.vk.imageprocessor.domain.usecase.GetImage
import konnov.commr.vk.imageprocessor.domain.usecase.SaveImage
import konnov.commr.vk.imageprocessor.domain.usecase.TransformImage
import konnov.commr.vk.imageprocessor.util.ViewState
import konnov.commr.vk.imageprocessor.util.ViewStateEmpty
import konnov.commr.vk.imageprocessor.util.ViewStateSuccess

class MainViewModel(
    private val useCaseHandler: UseCaseHandler,
    private val getImage: GetImage,
    private val saveImage: SaveImage,
    private val transformImage: TransformImage
): ViewModel() {

    val liveData = MutableLiveData<ViewState>()

    fun transformImage(bitmap: Bitmap, transformOption: Int) {
        val requestValue = TransformImage.RequestValues(bitmap, transformOption)
        useCaseHandler.execute(transformImage, requestValue,
            object : UseCase.UseCaseCallback<TransformImage.ResponseValue> {
                override fun onSuccess(response: TransformImage.ResponseValue) {
                    liveData.value = ViewStateSuccess(response.resultBitmap)
                }

                override fun onError() {
                    liveData.value = ViewStateEmpty(R.string.error)
                }
            })
    }

}