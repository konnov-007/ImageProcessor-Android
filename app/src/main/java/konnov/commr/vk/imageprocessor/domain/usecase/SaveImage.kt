package konnov.commr.vk.imageprocessor.domain.usecase

import konnov.commr.vk.imageprocessor.UseCase
import konnov.commr.vk.imageprocessor.data.source.ImageRepository

class SaveImage(
    private val imageRepository: ImageRepository
): UseCase<SaveImage.RequestValues, SaveImage.ResponseValue>() {
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