package com.example.homework_2.presentation.channels.child.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.databinding.ExpandableParentBinding
import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.presentation.delegate.AdapterDelegate
import com.example.homework_2.presentation.delegate.DelegateItem

class StreamDelegate(
    private val onItemClick: (StreamItem) -> Unit,
): AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpandableParentBinding.inflate(inflater, parent, false)
        return StreamHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int,
    ) {
        (holder as StreamHolder).bind(item.content() as StreamItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is StreamDelegateItem

    inner class StreamHolder(
        private val viewBinding: ExpandableParentBinding
    ): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: StreamItem){
            viewBinding.apply {
                nameStream.text = "#${item.name}"
//                itemView.setOnClickListener{
//                    if (expandedPosition == position) {
//                        expandedPosition = RecyclerView.NO_POSITION
//                    } else {
//                        expandedPosition = position
//                        onItemClick.invoke(streams[position])
//                    }
//                    notifyItemChanged(position)
//                }
                root.setOnClickListener{
                    onItemClick(item)
                }
            }
        }
    }
}