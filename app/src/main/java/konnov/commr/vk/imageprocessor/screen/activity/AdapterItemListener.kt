package konnov.commr.vk.imageprocessor.screen.activity

import konnov.commr.vk.imageprocessor.domain.entities.Image

interface AdapterItemListener {
    fun onItemClick(image: Image)
}