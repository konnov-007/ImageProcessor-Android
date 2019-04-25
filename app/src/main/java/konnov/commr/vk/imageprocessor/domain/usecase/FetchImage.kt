package konnov.commr.vk.imageprocessor.domain.usecase

import konnov.commr.vk.imageprocessor.UseCase
import konnov.commr.vk.imageprocessor.data.source.ImageRepository

class FetchImage(
    private val imageRepository: ImageRepository
): UseCase<FetchImage.RequestValues, FetchImage.ResponseValue>() {
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