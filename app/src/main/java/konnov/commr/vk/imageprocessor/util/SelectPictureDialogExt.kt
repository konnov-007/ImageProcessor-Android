package konnov.commr.vk.imageprocessor.util

import android.content.Intent
import android.provider.MediaStore
import konnov.commr.vk.imageprocessor.screen.dialogs.SelectPictureDialogFragment

/**
 * request codes for startActivityForResult
 */
const val GALLERY = 0
const val CAMERA = 1

fun SelectPictureDialogFragment.takePhotoFromCamera() {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivityForResult(intent, CAMERA)
}

fun SelectPictureDialogFragment.chooseFromGallery() {
    val galleryIntent = Intent(
        Intent.ACTION_PICK,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    startActivityForResult(galleryIntent, GALLERY)
}
