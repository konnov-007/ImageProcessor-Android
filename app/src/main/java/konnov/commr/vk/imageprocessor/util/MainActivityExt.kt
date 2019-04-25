package konnov.commr.vk.imageprocessor.util
/**
 * All the showing select image dialog logic and asking permissions logic is here
 */
import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.ComponentName
import android.graphics.drawable.ColorDrawable
import android.view.View
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.imageprocessor.screen.activity.MainActivity


const val GALLERY = 1
const val CAMERA = 2

const val REQUEST_ID_MULTIPLE_PERMISSIONS = 1

/**
 * Here we write all the permissions the app requires
 */
val requiredPermissions = arrayOf(Manifest.permission.CAMERA,
    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)



fun MainActivity.checkRuntimePermissions(){
    checkAndRequestPermissions()
}

/**
 * @return true if all the permissions have already been granted, false if we need to request the permissions
 */
private fun MainActivity.checkAndRequestPermissions(): Boolean {
    val permissionsToRequest = ArrayList<String>()

    for(requiredPermission in requiredPermissions) {
        if(ContextCompat.checkSelfPermission(this, requiredPermission) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(requiredPermission)
        }
    }

    if (permissionsToRequest.isNotEmpty()) {
        ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(),
            REQUEST_ID_MULTIPLE_PERMISSIONS
        )
        return false
    }
    return true
}

private fun MainActivity.showDialogOK(message: String, okListener: DialogInterface.OnClickListener) {
    androidx.appcompat.app.AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(resources.getString(android.R.string.ok), okListener)
        .setNegativeButton(resources.getString(android.R.string.cancel), okListener)
        .create()
        .show()
}

private fun MainActivity.explain(msg: String) {
    val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
    dialog.setMessage(msg)
        .setPositiveButton(resources.getString(android.R.string.yes)) { paramDialogInterface, paramInt ->
            startSettings()
        }
        .setNegativeButton(resources.getString(android.R.string.cancel)) { paramDialogInterface, paramInt -> finish() }
    dialog.show()
}

private fun MainActivity.startSettings(){
    val componetName = ComponentName(
        "com.android.settings",
        "com.android.settings.applications.InstalledAppDetails"
    )
    intent = Intent()
    intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
    intent.data = Uri.parse("konnov.commr.vk.imageprocessor")
    intent.component = componetName
    startActivity(intent)
}


fun MainActivity.permissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray){
    when (requestCode) {
        REQUEST_ID_MULTIPLE_PERMISSIONS -> {
            val permsMap = HashMap<String, Int>()
            // Initialize the permissions map
            for(requiredPermission in requiredPermissions) {
                permsMap[requiredPermission] = PackageManager.PERMISSION_GRANTED
            }
            // Fill with the actual results from the user
            if (grantResults.isNotEmpty()) {
                for (i in permissions.indices) {
                    permsMap[permissions[i]] = grantResults[i]
                }
                //if some permissions were not granted by user
                if(!checkPermissionMapAllGranted(permsMap)) {
                    if (shouldShowRequestPermissionRationale(this)) {
                        showDialogOK(resources.getString(R.string.permissions_required),
                            DialogInterface.OnClickListener { dialog, which ->
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> checkAndRequestPermissions()
                                    DialogInterface.BUTTON_NEGATIVE -> finish()
                                }
                            })
                    } else {
                        explain(resources.getString(R.string.mandatory_permissions_warning))
                    }
                }
            }
        }
    }
}

private fun checkPermissionMapAllGranted(permissions : HashMap<String, Int>) : Boolean {
    for(permission in permissions.values) {
        if(permission != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }
    return true
}

private fun shouldShowRequestPermissionRationale(activity: Activity) : Boolean {
    for(permission in requiredPermissions) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            return true
        }
    }
    return false
}


fun MainActivity.showPictureDialog(){//TODO rebuild it into a dialog fragment like [ItemClickDialogFragment]
    val pictureDialog = AlertDialog.Builder(this)
    pictureDialog.setTitle(resources.getString(R.string.select_action))
    val pictureDialogItems = arrayOf(resources.getString(R.string.pic_from_gallery),
        resources.getString(R.string.capture_from_camera))
    pictureDialog.setItems(pictureDialogItems
    ) { dialog, which ->
        when (which) {
            0 -> choosePhotoFromGallery()
            1 -> takePhotoFromCamera()
        }
    }
    pictureDialog.show()
}

private fun MainActivity.choosePhotoFromGallery() {
    val galleryIntent = Intent(
        Intent.ACTION_PICK,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    startActivityForResult(galleryIntent, GALLERY)
}

private fun MainActivity.takePhotoFromCamera() {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivityForResult(intent, CAMERA)
}


fun MainActivity.removeBackground(view: View) {
    if (view.background != transparentBackground) {
        view.background = transparentBackground
    }

}

private val MainActivity.transparentBackground: ColorDrawable
    get() = ColorDrawable(ContextCompat.getColor(this, R.color.transparent))
