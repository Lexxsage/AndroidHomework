package com.example.homework4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.databinding.ListTile2Binding
import com.example.homework4.databinding.ListTileBinding
import com.google.android.material.snackbar.Snackbar

class ListTileAdapter(val context: Context, var list:ArrayList<PlaceholderItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_ONE){
            return ListTileViewHolder(
                ListTile2Binding.inflate(LayoutInflater.from(context),
                    parent,
                    false)
                )
        }
        return ListTile2ViewHolder(
            ListTileBinding.inflate(LayoutInflater.from(context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].type == VIEW_ONE) {
            (holder as ListTileViewHolder).bind(list[position])
        } else {
            (holder as ListTile2ViewHolder).bind(list[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }
    inner class ListTileViewHolder(
        private val binding: ListTile2Binding
    ):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlaceholderItem) {
            binding.title.text = item.title
            binding.subtitle.text = item.subtitle
            binding.leading.setImageDrawable(item.image)
            binding.root.setOnClickListener { clickSnack(binding.root, binding.title.text) }
            if (item.color)
                binding.subtitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.red))
            else
                binding.subtitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.grey))
        }
    }

    inner class ListTile2ViewHolder(
        private val binding: ListTileBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(item: PlaceholderItem){
            binding.title.text = item.title
            binding.subtitle.text  = item.subtitle
            binding.leading.setImageDrawable(item.image)
            binding.root.setOnClickListener{clickSnack(binding.root, binding.title.text)}
            if (item.color)
                binding.subtitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.red))
            else
                binding.subtitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.grey))

        }
    }

    fun clickSnack(view: View, char: CharSequence){
        Snackbar.make(view, char, Snackbar.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    companion object{
        const val VIEW_ONE = 1
        const val VIEW_TWO = 2
    }

}