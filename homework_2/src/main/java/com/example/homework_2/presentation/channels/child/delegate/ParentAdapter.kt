package com.example.homework_2.presentation.channels.child.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.data.network.model.channels.stream.StreamItemApi
import com.example.homework_2.databinding.ExpandableParentBinding
import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.presentation.channels.parent.ParentDiffUtil

class ParentAdapter(private val onItemClick: (StreamItem) -> Unit):
    ListAdapter<StreamItem, ParentAdapter.ViewHolder>(ParentDiffUtil()){
    private var streams: List<StreamItem> = emptyList()
    private var expandedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpandableParentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(streams[position])
    }

    override fun getItemCount(): Int = streams.size

    inner class ViewHolder(private val binding: ExpandableParentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: StreamItem){
            binding.apply {
                nameStream.text = item.name
                itemView.setOnClickListener{
                    if (expandedPosition == position) {
                        expandedPosition = RecyclerView.NO_POSITION
                    } else {
                        expandedPosition = position
                        onItemClick.invoke(streams[position])
                    }
                    notifyItemChanged(position)
                }
                root.setOnClickListener{
                    onItemClick(item)
                }
            }
        }
    }

    fun search(list: List<StreamItem>) {
        streams = list
        notifyDataSetChanged()
    }
}