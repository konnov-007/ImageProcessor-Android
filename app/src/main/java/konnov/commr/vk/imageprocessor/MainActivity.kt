package konnov.commr.vk.imageprocessor

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input_image_button.setOnClickListener {
            showPictureDialog()
        }
    }

    //Called when user selects image or takes a picture with camera
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
                val picture = data.extras!!.get("data") as Bitmap
                input_image_button!!.setImageBitmap(picture)
            }
        }
    }

}
