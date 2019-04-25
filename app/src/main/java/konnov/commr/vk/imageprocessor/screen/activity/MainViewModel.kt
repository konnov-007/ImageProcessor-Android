package konnov.commr.vk.imageprocessor.screen.activity

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.imageprocessor.UseCase
import konnov.commr.vk.imageprocessor.UseCaseHandler
import konnov.commr.vk.imageprocessor.domain.model.Image
import konnov.commr.vk.imageprocessor.domain.usecase.FetchImage
import konnov.commr.vk.imageprocessor.domain.usecase.SaveImage
import konnov.commr.vk.imageprocessor.domain.usecase.TransformImage
import konnov.commr.vk.imageprocessor.util.*

class MainViewModel(
    private val useCaseHandler: UseCaseHandler,
    private val fetchImage: FetchImage, //this will later be used for fetching image from URL
    private val saveImage: SaveImage, //this will later be used for persisting image in the storage
    private val transformImage: TransformImage
): ViewModel() {

    val resultImages = ArrayList<Image>()

    val resultImageLiveData = MutableLiveData<ResultImageState>()

    val sourceImageLiveData = MutableLiveData<SourceImageState>()

    fun transformImage(bitmap: Bitmap, transformOption: Int) {
        val requestValue = TransformImage.RequestValues(bitmap, transformOption)
        useCaseHandler.execute(transformImage, requestValue,
            object : UseCase.UseCaseCallback<TransformImage.ResponseValue> {
                override fun onSuccess(response: TransformImage.ResponseValue) {
                    resultImages.add(0, Image(response.resultBitmap))
                    resultImageLiveData.value = ResultImageStateSuccess(resultImages)
                }

                override fun onError() {
                    resultImageLiveData.value = ResultImageStateEmpty(R.string.error)
                }
            })
    }

    fun fetchImage(url: String) {
        val requestValue = FetchImage.RequestValues(url)
        useCaseHandler.execute(fetchImage, requestValue,
            object : UseCase.UseCaseCallback<FetchImage.ResponseValue> {
                override fun onSuccess(response: FetchImage.ResponseValue) {
                    sourceImageLiveData.value = ImageStateSuccess(response.resultImage.bitmap)
                }

                override fun onError() {
                    sourceImageLiveData.value = ImageStateEmpty(R.string.error)
                }

            })

    }

    /**
     * Method for communicating between
     * [konnov.commr.vk.imageprocessor.screen.selectpicturedialog.SelectPictureDialogFragment]
     * and
     * [MainActivity]
     */
    fun imageSelected(bitmap: Bitmap) {
        sourceImageLiveData.value = ImageStateSuccess(bitmap)
    }

    fun imageDeleted(image: Image) {
        resultImages.remove(image)
        resultImageLiveData.value = ResultImageStateSuccess(resultImages)
    }

}