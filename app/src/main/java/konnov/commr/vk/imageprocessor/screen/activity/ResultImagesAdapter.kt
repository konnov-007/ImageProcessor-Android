package konnov.commr.vk.imageprocessor.screen.activity

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import konnov.commr.vk.imageprocessor.R
import konnov.commr.vk.imageprocessor.domain.model.Image
import kotlinx.android.synthetic.main.image_item.view.*

class ResultImagesAdapter(private var resultImages: ArrayList<Image> = ArrayList(),
                          private val itemListener: AdapterItemListener
) :
    RecyclerView.Adapter<ResultImagesAdapter.ResultImageViewHolder>() {

    override fun getItemCount(): Int {
        return resultImages.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ResultImageViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)

        return ResultImageViewHolder(view)
    }

    override fun onBindViewHolder(resultImageViewHolder: ResultImageViewHolder, position: Int) {
        resultImageViewHolder.image.setImageBitmap(resultImages[position].bitmap)
        resultImageViewHolder.image.setOnClickListener {
            itemListener.onItemClick(position, resultImages[position].bitmap)
        }
    }

    class ResultImageViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.image_item
    }

    fun updateList(images: ArrayList<Image>) {
        resultImages = ArrayList(images)
        notifyDataSetChanged()
    }
}
