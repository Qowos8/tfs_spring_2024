package com.example.homework_2.presentation.channels.child

import android.graphics.Color.argb
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.homework_2.presentation.channels.AllStreamItem
import com.example.homework_2.presentation.channels.TopicItem
import com.example.homework_2.databinding.ExpandableChildBinding
import com.example.homework_2.databinding.ExpandableParentBinding
import com.example.homework_2.databinding.ShimmerExpandableChildBinding
import java.util.Random

class StreamAdapter(
    private val onItemClick: (TopicItem) -> Unit,
    private val onStreamClick: (AllStreamItem) -> Unit
) : BaseExpandableListAdapter() {

    private var streams: List<AllStreamItem> = mutableListOf()
    private var topics: List<TopicItem> = emptyList()
    private val expandedGroups = mutableListOf<Int>()
    private var isLoading: Boolean = false
    private var lastExpandedGroupPosition: Int? = null

    override fun onGroupCollapsed(groupPosition: Int) {
        super.onGroupCollapsed(groupPosition)
        lastExpandedGroupPosition = null
    }

    override fun onGroupExpanded(groupPosition: Int) {
        super.onGroupExpanded(groupPosition)
        lastExpandedGroupPosition = groupPosition
    }

    fun collapseLastExpandedGroup() {
        lastExpandedGroupPosition?.let {
            onGroupCollapsed(it)
        }
    }

    override fun getGroupCount(): Int {
        return streams.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return topics.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return streams[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return topics[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val parentItem = getGroup(groupPosition) as AllStreamItem
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ExpandableParentBinding.inflate(inflater)
        binding.apply {
            nameStream.text = parentItem.name
            root.setOnClickListener {
                root.setOnClickListener {
                    if (expandedGroups.contains(groupPosition)) {
                        expandedGroups.remove(groupPosition)
                    } else {
                        expandedGroups.add(groupPosition)
                    }
                    notifyDataSetChanged()
                    onStreamClick(parentItem)
                }
                root.isActivated = expandedGroups.contains(groupPosition)
            }
            return root
        }

    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        //val childItem = getChild(groupPosition, childPosition) as TopicItem
        val inflater = LayoutInflater.from(parent?.context)
        //val binding = ExpandableChildBinding.inflate(inflater)

        if (isLoading) {
            return ShimmerExpandableChildBinding.inflate(inflater).root
        } else {
            val childItem = getChild(groupPosition, childPosition) as TopicItem
            val binding = ExpandableChildBinding.inflate(inflater)

            binding.apply {
                nameTopic.text = childItem.name
                messageCount.text = childItem.messageCount.toString()
                setChildColor(this)
                root.setOnClickListener {
                    onItemClick(childItem)
                }
                return root
            }
        }
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    private fun setChildColor(binding: ExpandableChildBinding) {
        val random = Random()
        val color = argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        binding.expandableChild.setBackgroundColor(color)
    }

    fun search(list: List<AllStreamItem>) {
        streams = list
        notifyDataSetChanged()
    }

    fun updateTopic(topicList: List<TopicItem>){
        topics = topicList
        if (topicList.isNotEmpty()) {
            notifyDataSetChanged()
        }
    }

    fun setLoading(loading: Boolean) {
        isLoading = loading
        notifyDataSetChanged()
    }
}
