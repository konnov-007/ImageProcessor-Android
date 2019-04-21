package konnov.commr.vk.imageprocessor

import konnov.commr.vk.imageprocessor.data.source.ImageRepository
import konnov.commr.vk.imageprocessor.domain.usecase.GetImage
import konnov.commr.vk.imageprocessor.domain.usecase.SaveImage
import konnov.commr.vk.imageprocessor.domain.usecase.TransformImage
import konnov.commr.vk.bitmapprocessor.BitmapProcessor

object Injection {

    fun provideRepository(): ImageRepository {
        return ImageRepository
    }

    fun provideUseCaseHandler(): UseCaseHandler {
        return UseCaseHandler.instance
    }

    fun provideGetImage(repository: ImageRepository): GetImage{
        return GetImage(repository)
    }

    fun provideSaveImage(repository: ImageRepository): SaveImage{
        return SaveImage(repository)
    }

    fun provideTransformImage() : TransformImage{
        return TransformImage(BitmapProcessor)
    }

}