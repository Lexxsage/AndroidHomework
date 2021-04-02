package com.example.homework4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.databinding.ListTileBinding
import com.example.homework4.databinding.ListTileLineBinding
import com.google.android.material.snackbar.Snackbar

class ItemAdapter(val context: Context, val listItem: ArrayList<ListItem>) :  RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    companion object {
        const val VIEW_ONE = 1
        const val VIEW_TWO = 2
    }

    class ItemViewHolder(
            private val binding: ListTileBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem) {
            binding.leading.setImageResource(item.image)
            binding.title.text = item.title
            binding.subtitle.text = item.subtitle
        }
    }

    class ItemViewLinearHolder(
            private val binding: ListTileLineBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem) {
            binding.leadingline.setImageResource(item.image)
            binding.titleline.text = item.title
            binding.subtitleline.text = item.subtitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TWO) {
            return ItemViewHolder(
                ListTileBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            )
        }
        return ItemViewLinearHolder(
            ListTileLineBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun getItemCount(): Int = listItem.size

    override fun getItemViewType(position: Int): Int {
        return listItem[position].viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            var snackbarText = listItem[position].title
            Snackbar.make(it, "$snackbarText", Snackbar.LENGTH_SHORT).show()
        }
        if (listItem[position].viewType === VIEW_TWO) {
            (holder as ItemViewHolder).bind(listItem[position])

        } else {
            (holder as ItemViewLinearHolder).bind(listItem[position])
        }
    }
}
