package konnov.commr.vk.imageprocessor.domain.usecase

import android.graphics.Bitmap
import konnov.commr.vk.imageprocessor.UseCase
import konnov.commr.vk.imageprocessor.data.source.ImageLoadedCallback
import konnov.commr.vk.imageprocessor.data.source.ImageRepository
import konnov.commr.vk.imageprocessor.domain.entities.Image

class FetchImage(
    private val imageRepository: ImageRepository
): UseCase<FetchImage.RequestValues, FetchImage.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues?) {
        imageRepository.getImage(requestValues!!.url, object: ImageLoadedCallback { //TODO replace this with rx java
            override fun bitmapLoaded(bitmap: Bitmap) {
                val responseValue = ResponseValue(Image(bitmap))
                useCaseCallback!!.onSuccess(responseValue)
            }

            override fun bitmapFailed() {
                useCaseCallback!!.onError()
            }

        })
    }

    class RequestValues(val url: String) : UseCase.RequestValues

    class ResponseValue(val resultImage: Image) : UseCase.ResponseValue
}