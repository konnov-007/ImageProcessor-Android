package konnov.commr.vk.imageprocessor

import android.app.AlertDialog
import android.content.Intent
import android.provider.MediaStore

const val GALLERY = 1
const val CAMERA = 2

fun MainActivity.showPictureDialog(){
    val pictureDialog = AlertDialog.Builder(this)
    pictureDialog.setTitle(resources.getString(R.string.select_action))
    val pictureDialogItems = arrayOf(resources.getString(R.string.pic_from_gallery),
        resources.getString(R.string.capture_from_camera))
    pictureDialog.setItems(pictureDialogItems
    ) { dialog, which ->
        when (which) {
            0 -> choosePhotoFromGallery()
            1 -> takePhotoFromCamera()
        }
    }
    pictureDialog.show()
}

fun MainActivity.choosePhotoFromGallery() {
    val galleryIntent = Intent(
        Intent.ACTION_PICK,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    startActivityForResult(galleryIntent, GALLERY)
}

private fun MainActivity.takePhotoFromCamera() {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivityForResult(intent, CAMERA)
}