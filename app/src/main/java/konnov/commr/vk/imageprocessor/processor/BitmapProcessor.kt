package konnov.commr.vk.imageprocessor.processor

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.ColorMatrixColorFilter


object BitmapProcessor {
    private const val FLIP_HORIZONTAL = 1
    private const val FLIP_VERTICAL = 2

    fun transformBitmap(sourceBitmap: Bitmap, transformationType: Int): Bitmap? {
        return when(transformationType) {
            MIRROR -> mirrorBitmap(sourceBitmap)
            ROTATE -> rotateBitmap(sourceBitmap)
            INVERT -> invertColors(sourceBitmap)
            else -> null
        }
    }

    private fun mirrorBitmap(sourceBitmap: Bitmap, type: Int = FLIP_HORIZONTAL): Bitmap {
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

    private fun rotateBitmap(sourceBitmap: Bitmap, angle: Float = 90f): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            sourceBitmap, 0, 0, sourceBitmap.width,
            sourceBitmap.height, matrix, true
        )
    }

    private fun invertColors(sourceBitmap: Bitmap): Bitmap {
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