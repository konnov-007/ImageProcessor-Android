package konnov.commr.vk.imageprocessor.domain.usecase

import android.graphics.Bitmap
import konnov.commr.vk.imageprocessor.UseCase
import konnov.commr.vk.bitmapprocessor.BitmapProcessor

/**
 * Use case for transforming image (inverting colors, rotating, mirroring)
 */
class TransformImage(
    private val bitmapProcessor: BitmapProcessor
) : UseCase<TransformImage.RequestValues, TransformImage.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues?) {
        val resultBitmap = bitmapProcessor.transformBitmap(requestValues!!.bitmap, requestValues.transformOption) //TODO do this in rx java in background thread
        val responseValues = ResponseValue(resultBitmap!!)
        useCaseCallback!!.onSuccess(responseValues) //todo add calling onError if something went wrong
    }

    class RequestValues(val bitmap: Bitmap, val transformOption: Int) : UseCase.RequestValues

    class ResponseValue(val resultBitmap: Bitmap) : UseCase.ResponseValue
}