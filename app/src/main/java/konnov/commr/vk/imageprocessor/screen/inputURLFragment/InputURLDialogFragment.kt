package konnov.commr.vk.imageprocessor.screen.inputURLFragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.imageprocessor.screen.activity.MainActivity
import konnov.commr.vk.imageprocessor.screen.activity.MainViewModel
import konnov.commr.vk.imageprocessor.util.isUrlValid
import konnov.commr.vk.imageprocessor.util.showMessage
import kotlinx.android.synthetic.main.dialog_input_url.*

class InputURLDialogFragment(private val mainViewModel: MainViewModel? = null): DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_input_url, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        dialog?.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes?.windowAnimations = R.style.DialogAnimation

        }

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

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(resources.getDimensionPixelSize(R.dimen.dialog_width), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}