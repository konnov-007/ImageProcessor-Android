package konnov.commr.vk.imageprocessor.screen.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.imageprocessor.screen.activity.MainActivity
import konnov.commr.vk.imageprocessor.screen.activity.MainViewModel
import konnov.commr.vk.imageprocessor.util.isUrlValid
import konnov.commr.vk.imageprocessor.util.showMessage
import kotlinx.android.synthetic.main.dialog_input_url.*

class InputURLDialogFragment(private val mainViewModel: MainViewModel? = null): BaseDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_input_url, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ok_tv.setOnClickListener{
            val url = url_et.text.toString()
            if(isUrlValid(url)) {
                mainViewModel?.fetchImage(url)
                dismiss()
            } else {
                (activity as MainActivity).showMessage(resources.getString(R.string.wrong_url))
            }
        }
    }

}