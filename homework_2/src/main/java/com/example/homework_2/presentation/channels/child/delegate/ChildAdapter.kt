package com.example.homework_2.presentation.channels.child.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.presentation.channels.TopicItem
import com.example.homework_2.presentation.channels.child.ChildDiffUtil
import com.example.homework_2.databinding.ExpandableChildBinding

class ChildAdapter(private val onItemClick: (TopicItem) -> Unit) :
    ListAdapter<TopicItem, ChildAdapter.ViewHolder>(
        ChildDiffUtil()
    ) {
    private var topics: List<TopicItem> = emptyList()
    private var currentColor = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpandableChildBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(topics[position])
    }

    override fun getItemCount(): Int = topics.size

    inner class ViewHolder(private val binding: ExpandableChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TopicItem) {
            binding.apply {
                messageCount.text = item.messageCount.toString()
                nameTopic.text = item.name
                expandableChild.setBackgroundColor(currentColor)
                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

    fun setColor(color: Int){
        currentColor = color
    }

    fun update(items: List<TopicItem>) {
        topics = items
        notifyDataSetChanged()
    }
}