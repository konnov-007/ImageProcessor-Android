package konnov.commr.vk.imageprocessor.screen

import androidx.lifecycle.ViewModel
import konnov.commr.vk.imageprocessor.domain.usecase.GetImage
import konnov.commr.vk.imageprocessor.domain.usecase.SaveImage
import konnov.commr.vk.imageprocessor.domain.usecase.TransformImage

class MainViewModel(
    private val getImage: GetImage,
    private val saveImage: SaveImage,
    private val transformImage: TransformImage
): ViewModel() {
}