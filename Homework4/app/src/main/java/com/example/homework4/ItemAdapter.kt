package com.example.homework4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.PlaceHolderContent.ListItem
import com.example.homework4.databinding.ListItemBinding
import com.example.homework4.databinding.ListItemLineBinding
import com.google.android.material.snackbar.Snackbar


class ItemAdapter : ListAdapter<ListItem, ItemAdapter.ItemViewHolder>(DiffCallback) {

    private val viewTile = 1
    private val viewTileLine = 2

    class ItemViewHolder(
        private val binding: ListItemBinding,
        private val hasImage: Boolean,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem) {
            binding.tile.leading = if (hasImage) item.image else null
            binding.tile.title = item.title
            binding.tile.subtitle = item.subtitle
        }
    }

    class ItemViewLinearHolder(
            private val binding: ListItemLineBinding,
            private val hasImage: Boolean,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem) {
            binding.tileline.leading = if (hasImage) item.image else null
            binding.tileline.title = item.title
            binding.tileline.subtitle = item.subtitle
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return if (viewType == 1){
            ItemViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),viewType == 0)
        } else { ItemViewLinearHolder(ListItemLineBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewType==0)}
    }

    object DiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(
            oldItem: ListItem,
            newItem: ListItem,
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ListItem,
            newItem: ListItem,
        ) = oldItem == newItem
    }
}
