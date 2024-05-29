package com.example.homework_2.presentation.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.domain.entity.TopicItem
import com.example.homework_2.presentation.channels.child.delegate.StreamDelegateItem
import com.example.homework_2.presentation.channels.child.delegate.TopicDelegateItem
import com.example.homework_2.utils.generateUniqueId

class MainAdapter :
    ListAdapter<DelegateItem, RecyclerView.ViewHolder>(DelegateAdapterItemCallback()) {
    private val delegates: MutableList<AdapterDelegate> = mutableListOf()
    private val delegateItems: MutableList<DelegateItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].onBindViewHolder(holder, getItem(position), position)
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { it.isOfViewType(currentList[position]) }
    }

    fun addDelegate(delegate: AdapterDelegate) {
        delegates.add(delegate)
    }

    fun toggleTopicsForStream(streamId: Int, topics: List<TopicItem>) {
        val currentList = currentList.toMutableList()
        val streamIndex =
            currentList.indexOfFirst { it is StreamDelegateItem && (it.content() as StreamItem).id == streamId }
        if (streamIndex == -1) {
            return
        }
        val nextIndex = streamIndex + 1

        if (nextIndex < itemCount && currentList[nextIndex] is TopicDelegateItem) {
            val topicsToRemove = currentList.subList(nextIndex, currentList.size)
                .takeWhile { it is TopicDelegateItem }
            currentList.removeAll(topicsToRemove)
        } else {
            val topicsToAdd = topics.map { TopicDelegateItem(
                id = generateUniqueId(it.messageCount, streamId),
                value = it)
            }

            currentList.addAll(nextIndex, topicsToAdd)
        }

        submitList(currentList.toList())
    }

    fun getMessageTimestamp(position: Int): Long {
        val messageItem = (getItem(position).content() as MessageItem)
        return messageItem.timestamp
    }
}