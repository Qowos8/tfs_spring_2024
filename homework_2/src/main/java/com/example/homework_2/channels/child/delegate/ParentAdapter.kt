package com.example.homework_2.channels.child.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.channels.AllStreamItem
import com.example.homework_2.channels.child.ParentDiffUtil
import com.example.homework_2.databinding.ExpandableParentBinding

class ParentAdapter(private val onItemClick: (AllStreamItem) -> Unit):
    ListAdapter<AllStreamItem, ParentAdapter.ViewHolder>(ParentDiffUtil()){
    private var streams: List<AllStreamItem> = emptyList()
    private var expandedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpandableParentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(streams[position])
        holder.itemView.setOnClickListener{
//            onItemClick.invoke(streams[position])
//            notifyDataSetChanged()
            if (expandedPosition == position) {
                expandedPosition = RecyclerView.NO_POSITION
            } else {
                expandedPosition = position
                onItemClick.invoke(streams[position])
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = streams.size

    inner class ViewHolder(private val binding: ExpandableParentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: AllStreamItem){
            binding.apply {
                nameStream.text = item.name

                root.setOnClickListener{
                    onItemClick(item)
                }
            }
        }
    }

    fun search(list: List<AllStreamItem>) {
        streams = list
        notifyDataSetChanged()
    }
}