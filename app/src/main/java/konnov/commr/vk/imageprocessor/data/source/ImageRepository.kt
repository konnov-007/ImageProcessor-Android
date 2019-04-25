package konnov.commr.vk.imageprocessor.data.source

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception
import android.os.Looper



/**
 * Repository that will be used to save an image and to get an image from URL
 */

object ImageRepository {

    //TODO all the logic from here should be moved into a separate class - RemoteDataSource

    lateinit var mCallback: ImageLoadedCallback //temporary solution

    val target = object: Target{
        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            mCallback.bitmapFailed()
        }

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            mCallback.bitmapLoaded(bitmap)
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

        }
    }

    fun getImage(url: String, callback: ImageLoadedCallback) {  //TODO make an observable instead of callback
        val uiHandler = Handler(Looper.getMainLooper())
        mCallback = callback
        uiHandler.post {
            Picasso.get()
                .load(url)
                .into(target)
        }
    }
}