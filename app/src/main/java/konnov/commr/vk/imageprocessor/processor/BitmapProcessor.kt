package konnov.commr.vk.imageprocessor.processor

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.ColorMatrixColorFilter


object BitmapProcessor {

    val FLIP_HORIZONTAL = 1
    val FLIP_VERTICAL = 2

    fun mirrorBitmap(sourceBitmap: Bitmap, type: Int = FLIP_HORIZONTAL): Bitmap? {
        val matrix = Matrix()
        when (type) {
            FLIP_HORIZONTAL -> matrix.preScale(-1.0f, 1.0f)
            FLIP_VERTICAL -> matrix.preScale(1.0f, -1.0f)
        }
        return Bitmap.createBitmap(
            sourceBitmap, 0, 0, sourceBitmap.width,
            sourceBitmap.height, matrix, true
        )
    }

    fun rotateBitmap(sourceBitmap: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            sourceBitmap, 0, 0, sourceBitmap.width,
            sourceBitmap.height, matrix, true
        )
    }

    fun invertColors(sourceBitmap: Bitmap): Bitmap {
        val bmpMonochrome = Bitmap.createBitmap(sourceBitmap.width, sourceBitmap.height, sourceBitmap.config)
        val canvas = Canvas(bmpMonochrome)
        val ma = ColorMatrix()
        ma.setSaturation(0f)
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(ma)
        canvas.drawBitmap(sourceBitmap, 0f, 0f, paint)
        return bmpMonochrome
    }
}