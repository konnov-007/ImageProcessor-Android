package konnov.commr.vk.imageprocessor

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import konnov.commr.vk.imageprocessor.data.source.ImageRepository
import konnov.commr.vk.imageprocessor.screen.MainViewModel

class ViewModelFactory private constructor(
    private val imageRepository: ImageRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) -> {
                    val useCaseHandler = Injection.provideUseCaseHandler()
                    val getImage = Injection.provideGetImage(imageRepository)
                    val saveImage = Injection.provideSaveImage(imageRepository)
                    val transformImage = Injection.provideTransformImage()
                    MainViewModel(useCaseHandler, getImage, saveImage, transformImage)
                }
                else ->
                    throw IllegalArgumentException("Unknown class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideRepository() //if the repository will later need context we'll pass application.applicationContext) here
                ).also { INSTANCE = it }
            }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}