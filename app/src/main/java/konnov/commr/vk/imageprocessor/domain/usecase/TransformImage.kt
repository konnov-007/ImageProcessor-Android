package konnov.commr.vk.imageprocessor.domain.usecase

import konnov.commr.vk.imageprocessor.UseCase
import konnov.commr.vk.imageprocessor.processor.BitmapProcessor

/**
 * Use case for transforming image (inverting colors, rotating, mirroring)
 */
class TransformImage(
    private val bitmapProcessor: BitmapProcessor
) : UseCase<TransformImage.RequestValues, TransformImage.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues?) {
        //TODO implement
        }

    class RequestValues : UseCase.RequestValues {
        //TODO implement
    }

    class ResponseValue() : UseCase.ResponseValue {
        //TODO implement
    }
}