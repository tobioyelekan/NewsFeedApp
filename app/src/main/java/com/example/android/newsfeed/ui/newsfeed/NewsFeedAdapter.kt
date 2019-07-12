package com.example.android.newsfeed.ui.newsfeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.newsfeed.data.model.FeedResponse
import com.example.android.newsfeed.databinding.FeedItemBinding

class NewsFeedAdapter : ListAdapter<FeedResponse, NewsFeedAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FeedItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val property = getItem(position)
        holder.bind(property)
    }

    class ViewHolder(private var binding: FeedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feed: FeedResponse) {
            binding.feed = feed
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FeedResponse>() {
        override fun areItemsTheSame(oldItem: FeedResponse, newItem: FeedResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FeedResponse, newItem: FeedResponse): Boolean {
            return oldItem == newItem
        }
    }
}