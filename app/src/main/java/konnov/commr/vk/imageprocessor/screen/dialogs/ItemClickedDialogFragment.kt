package konnov.commr.vk.imageprocessor.screen.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.imageprocessor.domain.entities.Image
import konnov.commr.vk.imageprocessor.screen.activity.MainViewModel
import kotlinx.android.synthetic.main.dialog_item_clicked.*

class ItemClickedDialogFragment(private val mainViewModel: MainViewModel? = null, val image: Image? = null) :
    BaseDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_item_clicked, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reuse_tv.setOnClickListener {
            mainViewModel?.imageSelected(image!!.bitmap)
            dismiss()
        }

        delete_tv.setOnClickListener {
            mainViewModel?.imageDeleted(image!!)
            dismiss()
        }
    }
}