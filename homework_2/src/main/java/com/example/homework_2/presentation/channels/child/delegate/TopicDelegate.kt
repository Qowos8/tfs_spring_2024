package com.example.homework_2.presentation.channels.child.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.databinding.ExpandableChildBinding
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.delegate.AdapterDelegate
import com.example.homework_2.presentation.delegate.DelegateItem

class TopicDelegate(
    private val onItemClick: (TopicItem) -> Unit
): AdapterDelegate {
    private var currentColor = 0

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpandableChildBinding.inflate(inflater, parent, false)
        return TopicHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int,
    ) {
        (holder as TopicHolder).bind(item.content() as TopicItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is TopicDelegateItem

    inner class TopicHolder(
        private val viewBinding: ExpandableChildBinding
    ): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: TopicItem){
            viewBinding.apply {
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
}