package konnov.commr.vk.imageprocessor.screen.activity

import konnov.commr.vk.imageprocessor.domain.model.Image

interface AdapterItemListener {
    fun onItemClick(image: Image)
}