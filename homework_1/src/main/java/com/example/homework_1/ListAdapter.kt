package com.example.homework_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_1.databinding.ListItemBinding

internal class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var items: ArrayList<String> = arrayListOf()

    fun updateData(data: ArrayList<String>) {
        this.items.clear()
        this.items.addAll(data)
        notifyItemRangeChanged(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
    class ListViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.apply {
                event.text = item
            }
        }
    }
}
