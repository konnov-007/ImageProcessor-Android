package konnov.commr.vk.imageprocessor.screen.activity

import androidx.appcompat.app.AppCompatActivity
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.bitmapprocessor.INVERT
import konnov.commr.vk.bitmapprocessor.MIRROR
import konnov.commr.vk.bitmapprocessor.ROTATE
import konnov.commr.vk.imageprocessor.domain.model.Image
import konnov.commr.vk.imageprocessor.screen.itemclickdialog.ItemClickedDialogFragment
import konnov.commr.vk.imageprocessor.screen.selectpicturedialog.SelectPictureDialogFragment
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
        resultImagesAdapter =
            ResultImagesAdapter(itemListener = adapterItemListener)
        val linearLayoutManager = LinearLayoutManager(this)
        results_rv.layoutManager = linearLayoutManager
        results_rv.adapter = resultImagesAdapter

        mainViewModel = obtainViewModel()
        mainViewModel.sourceImageLiveData.observe(this, Observer<SourceImageState> { response -> updateSelectedPicture(response) })
        mainViewModel.resultImageLiveData.observe(this, Observer<ResultImageState> { response -> updateResultAdapter(response) })
    }

    override fun onClick(v: View?) {
        when(v) {
            input_image_button -> showDialogFragment(SelectPictureDialogFragment(mainViewModel))
            rotate_btn -> processPicture(ROTATE)
            invert_colors_btn -> processPicture(INVERT)
            mirror_btn -> processPicture(MIRROR)

        }
    }

    private fun processPicture(transformType: Int){
        if(imageIsSet(input_image_button.drawable)) {
            val sourceBitmap = (input_image_button.drawable as BitmapDrawable).bitmap
            mainViewModel.transformImage(sourceBitmap, transformType)
        } else {
            showDialogFragment(SelectPictureDialogFragment(mainViewModel))
        }
    }


    private fun updateResultAdapter(state: ResultImageState) {
        when (state) {
            is ResultImageStateSuccess -> resultImagesAdapter.updateList(state.resultImages)
            is ResultImageStateEmpty -> showMessage(resources.getString(state.message))
        }
    }

    private fun updateSelectedPicture(state: SourceImageState) {
        when (state) {
            is ImageStateSuccess -> {
                removeBackground(input_image_button)
                input_image_button.setImageBitmap(state.resultBitmap)
            }
            is ImageStateEmpty -> showMessage(resources.getString(state.message)) //this occur if getting image from URL fails
        }
    }

    /**
     * Listening for the clicks in ResultImagesAdapter
     */
    private val adapterItemListener = object : AdapterItemListener {
        override fun onItemClick(image: Image) {
            showDialogFragment(ItemClickedDialogFragment(mainViewModel, image))
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
