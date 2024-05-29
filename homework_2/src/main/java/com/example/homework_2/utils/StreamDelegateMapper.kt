package com.example.homework_2.utils

import com.example.homework_2.domain.entity.StreamItem
import com.example.homework_2.presentation.channels.child.delegate.StreamDelegateItem
import com.example.homework_2.presentation.delegate.DelegateItem

object StreamDelegateMapper {
    fun convertToDelegate(streams: List<StreamItem>): List<DelegateItem>{
        val delegateList: MutableList<DelegateItem> = mutableListOf()
        streams.forEach{ stream ->
            delegateList.add(
                StreamDelegateItem(
                    id = stream.id,
                    value = stream
                )
            )
        }
        return delegateList
    }
}