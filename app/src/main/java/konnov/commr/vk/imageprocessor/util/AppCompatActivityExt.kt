package konnov.commr.vk.imageprocessor.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import konnov.commr.vk.imageprocessor.ViewModelFactory

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)


fun AppCompatActivity.showMessage(message : String?){
    Snackbar.make(findViewById(android.R.id.content), message!!, Snackbar.LENGTH_LONG).show()
}

fun AppCompatActivity.showDialogFragment(dialog: DialogFragment) = dialog.show(supportFragmentManager, null)
