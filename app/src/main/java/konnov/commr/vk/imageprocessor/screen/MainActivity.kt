package konnov.commr.vk.imageprocessor.screen

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import konnov.commr.vk.imageprocessor.*
import konnov.commr.vk.imageprocessor.util.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI(){
        checkRuntimePermissions()
        input_image_button.setOnClickListener(this)
        rotate_btn.setOnClickListener(this)
        invert_colors_btn.setOnClickListener(this)
        mirror_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            input_image_button -> showPictureDialog()
            rotate_btn -> showMessage("Not implemented")
            invert_colors_btn -> showMessage("Not Implemented")
            mirror_btn -> showMessage("Not implemented")
        }
    }

    /**
     * Called when user selects image or takes a picture with camera
     */
    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data == null) {
            return
        }
        when (requestCode) {
            GALLERY -> {
                val contentURI = data.data
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                input_image_button!!.setImageBitmap(bitmap)
            }
            CAMERA -> {
                val thumbnail = data.extras!!.get("data") as Bitmap //here we only get a thumbnail of a picture https://stackoverflow.com/questions/36662676/camera-image-is-too-small
                input_image_button!!.setImageBitmap(thumbnail)
            }
        }
    }

    /**
     * Called when user accepts/denies permissions
     */
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        permissionsResult(requestCode, permissions, grantResults)
    }

}
