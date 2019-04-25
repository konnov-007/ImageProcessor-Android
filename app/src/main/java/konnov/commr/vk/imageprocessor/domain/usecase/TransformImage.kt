package konnov.commr.vk.imageprocessor.domain.usecase

import android.graphics.Bitmap
import konnov.commr.vk.imageprocessor.UseCase
import konnov.commr.vk.bitmapprocessor.BitmapProcessor
import konnov.commr.vk.imageprocessor.domain.model.Image

/**
 * Use case for transforming image (inverting colors, rotating, mirroring)
 */
class TransformImage(
    private val bitmapProcessor: BitmapProcessor
) : UseCase<TransformImage.RequestValues, TransformImage.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues?) {
        val resultBitmap = bitmapProcessor.transformBitmap(requestValues!!.bitmap, requestValues.transformOption)
        if(resultBitmap == null) {
            useCaseCallback!!.onError()
        } else {
            val responseValues = ResponseValue(Image(resultBitmap))
            useCaseCallback!!.onSuccess(responseValues)
        }
    }

    class RequestValues(val bitmap: Bitmap, val transformOption: Int) : UseCase.RequestValues

    class ResponseValue(val resultImage: Image) : UseCase.ResponseValue
}