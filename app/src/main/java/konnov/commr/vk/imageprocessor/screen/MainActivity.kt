package konnov.commr.vk.imageprocessor.screen

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.bitmapprocessor.INVERT
import konnov.commr.vk.bitmapprocessor.MIRROR
import konnov.commr.vk.bitmapprocessor.ROTATE
import konnov.commr.vk.imageprocessor.util.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var resultImagesAdapter: ResultImagesAdapter

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
        resultImagesAdapter = ResultImagesAdapter(itemListener = adapterItemListener)
        val linearLayoutManager = LinearLayoutManager(this)
        results_rv.layoutManager = linearLayoutManager
        results_rv.adapter = resultImagesAdapter

        mainViewModel = obtainViewModel()
        mainViewModel.liveData.observe(this, Observer<ViewState> { response -> updateViewState(response) })
    }

    override fun onClick(v: View?) {
        when(v) {
            input_image_button -> showPictureDialog()
            rotate_btn -> {
                if(imageIsSet(input_image_button.drawable)) {
                    val sourceBitmap = (input_image_button.drawable as BitmapDrawable).bitmap
                    mainViewModel.transformImage(sourceBitmap, ROTATE)
                } else {
                    showPictureDialog()
                }
            }
            invert_colors_btn -> {
               if(imageIsSet(input_image_button.drawable)) {
                   val sourceBitmap = (input_image_button.drawable as BitmapDrawable).bitmap
                   mainViewModel.transformImage(sourceBitmap, INVERT)
               } else {
                   showPictureDialog()
               }
            }
            mirror_btn -> {
                if(imageIsSet(input_image_button.drawable)) {
                    val sourceBitmap = (input_image_button.drawable as BitmapDrawable).bitmap
                    mainViewModel.transformImage(sourceBitmap, MIRROR)
                } else {
                    showPictureDialog()
                }
            }
        }
    }


    private fun updateViewState(state: ViewState) {
        when (state) {
            is ViewStateSuccess -> resultImagesAdapter.addBitmap(state.resultBitmap)
            is ViewStateEmpty -> showMessage(resources.getString(state.message))
        }
    }

    /**
     * Listening for the clicks in ResultImagesAdapter
     */
    private val adapterItemListener = object : AdapterItemListener {
        override fun onItemClick(itemPosition: Int, bitmap: Bitmap) {
            showMessage("clicked item $itemPosition")
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
                removeBackground(input_image_button) //removing the "click here to upload" background
                input_image_button!!.setImageBitmap(bitmap)
            }
            CAMERA -> {
                val thumbnail = data.extras!!.get("data") as Bitmap //here we only get a thumbnail of a picture https://stackoverflow.com/questions/36662676/camera-image-is-too-small
                removeBackground(input_image_button)
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

    private fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)

}
