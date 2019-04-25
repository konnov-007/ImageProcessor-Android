package konnov.commr.vk.imageprocessor.screen.itemclickdialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.imageprocessor.domain.model.Image
import konnov.commr.vk.imageprocessor.screen.activity.MainViewModel
import kotlinx.android.synthetic.main.dialog_item_clicked.*

class ItemClickedDialogFragment(private val mainViewModel: MainViewModel? = null, val image: Image? = null): DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_item_clicked, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes?.windowAnimations = R.style.DialogAnimation

            reuse_tv.setOnClickListener{
                mainViewModel?.imageSelected(image!!.bitmap)
                dismiss()
            }

            delete_tv.setOnClickListener {
                mainViewModel?.imageDeleted(image!!)
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(resources.getDimensionPixelSize(R.dimen.dialog_width), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}