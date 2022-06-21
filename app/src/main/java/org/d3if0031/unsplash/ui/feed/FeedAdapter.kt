package org.d3if0031.unsplash.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0031.unsplash.data.model.ImageResponse
import org.d3if0031.unsplash.databinding.ViewFeedImageBinding
import org.d3if0031.unsplash.ui.MainViewModel

class FeedAdapter(private val viewModel: MainViewModel) :
    ListAdapter<ImageResponse, FeedAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageResponse = getItem(position)
        holder.bind(imageResponse, viewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        private val binding: ViewFeedImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewFeedImageBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }

        fun bind(image: ImageResponse, viewModel: MainViewModel) = binding.apply {
            this.imageResponse = image
            this.root.setOnClickListener {
                viewModel.setSelectedImage(image)
            }

            executePendingBindings()
        }
    }

}

class TaskDiffCallback : DiffUtil.ItemCallback<ImageResponse>() {
    override fun areItemsTheSame(oldItem: ImageResponse, newItem: ImageResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageResponse, newItem: ImageResponse): Boolean {
        return oldItem == newItem
    }
}
