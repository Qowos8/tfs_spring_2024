package com.example.homework_2.channels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.homework_2.databinding.ExpandableChildBinding
import com.example.homework_2.databinding.ExpandableParentBinding
import com.example.homework_2.utils.ColorUtils.setColor

class StreamAdapter(
    private val onItemClick: (TopicItem) -> Unit,
) : BaseExpandableListAdapter() {
    private var streams: MutableList<StreamItem> = mutableListOf()

    override fun getGroupCount(): Int {
        return streams.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return streams[groupPosition].topics.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return streams[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return streams[groupPosition].topics[childPosition]
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
        val parentItem = getGroup(groupPosition) as StreamItem
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ExpandableParentBinding.inflate(inflater)
        binding.nameStream.text = parentItem.name
        return binding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val childItem = getChild(groupPosition, childPosition) as TopicItem
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ExpandableChildBinding.inflate(inflater)

        binding.apply {
            nameTopic.text = childItem.name
            childItem.parentId = getGroupId(groupPosition)
            setChildColor(childItem, this)
            root.setOnClickListener {
                onItemClick(childItem)
            }
            return root
        }
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    private fun setChildColor(childItem: TopicItem, binding: ExpandableChildBinding) {
        if (childItem.color === null) {
            setColor(childItem, binding.expandableChild)
        } else {
            binding.expandableChild.setBackgroundColor(childItem.color!!)
        }
    }

    fun update(list: MutableList<StreamItem>) {
        streams = list
        notifyDataSetChanged()
    }
}
