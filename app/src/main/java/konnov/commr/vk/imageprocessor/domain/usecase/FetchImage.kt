package konnov.commr.vk.imageprocessor.domain.usecase

import konnov.commr.vk.imageprocessor.UseCase
import konnov.commr.vk.imageprocessor.data.source.ImageRepository
import konnov.commr.vk.imageprocessor.domain.model.Image

class FetchImage(
    private val imageRepository: ImageRepository
): UseCase<FetchImage.RequestValues, FetchImage.ResponseValue>() {
    override fun executeUseCase(requestValues: RequestValues?) {
//        imageRepository.getImage() TODO implement method in repository
    }

    class RequestValues(url: String) : UseCase.RequestValues

    class ResponseValue(val resultImage: Image) : UseCase.ResponseValue
}