package konnov.commr.vk.imageprocessor.screen

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import konnov.commr.vk.imageprocessor.R
import kotlinx.android.synthetic.main.image_item.view.*

class ResultImagesAdapter(private val resultBitmaps: ArrayList<Bitmap> = ArrayList(),
                          private val itemListener: AdapterItemListener) :
    RecyclerView.Adapter<ResultImagesAdapter.ResultImageViewHolder>() {

    override fun getItemCount(): Int {
        return resultBitmaps.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ResultImageViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)

        return ResultImageViewHolder(view)
    }

    override fun onBindViewHolder(resultImageViewHolder: ResultImageViewHolder, position: Int) {
        resultImageViewHolder.image.setImageBitmap(resultBitmaps[position])
        resultImageViewHolder.image.setOnClickListener {
            itemListener.onItemClick(position, resultBitmaps[position])
        }
    }

    class ResultImageViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.image_item
    }

    fun addBitmap(bitmap: Bitmap) {
        resultBitmaps.add(0, bitmap)
        notifyDataSetChanged()
    }
}
