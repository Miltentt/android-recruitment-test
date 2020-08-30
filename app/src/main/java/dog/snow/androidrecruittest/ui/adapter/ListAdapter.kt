package dog.snow.androidrecruittest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.model.Detail
import dog.snow.androidrecruittest.ui.model.ListItem

class ListAdapter(private val onClick: (item: Detail, position: Int, view: View) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<Detail, ListAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ViewHolder(
        itemView: View,
        private val onClick: (item: Detail, position: Int, view: View) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: Detail) = with(itemView) {
            val ivThumb: ImageView = findViewById(R.id.iv_thumb)
            val tvTitle: TextView = findViewById(R.id.tv_photo_title)
            val tvAlbumTitle: TextView = findViewById(R.id.tv_album_title)
            tvTitle.text = item.photoTitle
            tvAlbumTitle.text = item.albumTitle
            //TODO: display item.thumbnailUrl in ivThumb
            setOnClickListener { onClick(item, adapterPosition, this) }
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Detail>() {
            override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Detail, newItem: Detail): Boolean =
                oldItem == newItem
        }
    }
}