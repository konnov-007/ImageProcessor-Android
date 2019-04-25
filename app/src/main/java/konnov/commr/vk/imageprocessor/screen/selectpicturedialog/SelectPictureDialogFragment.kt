package konnov.commr.vk.imageprocessor.screen.selectpicturedialog

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.imageprocessor.screen.activity.MainActivity
import konnov.commr.vk.imageprocessor.screen.activity.MainViewModel
import konnov.commr.vk.imageprocessor.screen.inputURLFragment.InputURLDialogFragment
import konnov.commr.vk.imageprocessor.util.*
import kotlinx.android.synthetic.main.dialog_select_picture.*

class SelectPictureDialogFragment(private val mainViewModel: MainViewModel? = null): DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_select_picture, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        dialog?.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes?.windowAnimations = R.style.DialogAnimation

            pic_gallery_tv.setOnClickListener{
                chooseFromGallery()
            }

            capture_camera_tv.setOnClickListener {
                takePhotoFromCamera()
            }

            load_URL_tv.setOnClickListener {
                (activity as MainActivity).showDialogFragment(InputURLDialogFragment(mainViewModel))
            }
        }
    }

    /**
     * Called when user selects image or takes a picture with camera
     */
    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data == null) {
            return
        }
        when (requestCode) {
            GALLERY -> {
                val contentURI = data.data
                val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, contentURI)
                mainViewModel?.imageSelected(bitmap)
                dismiss()
            }

            CAMERA -> {
                val thumbnail = data.extras?.get("data") as Bitmap //here we only get a thumbnail of a picture https://stackoverflow.com/questions/36662676/camera-image-is-too-small
                mainViewModel?.imageSelected(thumbnail)
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(resources.getDimensionPixelSize(R.dimen.dialog_width), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}