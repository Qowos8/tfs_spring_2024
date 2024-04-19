package com.example.homework_2.utils

import com.example.homework_2.data.network.model.AllStreamItem
import com.example.homework_2.data.network.model.SubStreamItem

object MapperAllToSub{
    fun mapAllToSub(subStreamItem: List<SubStreamItem>): List<AllStreamItem> {
        val sub: MutableList<AllStreamItem> = mutableListOf()
        for (i in 0..subStreamItem.size) {
            sub.add(
                AllStreamItem(
                    id = subStreamItem[i].streamId,
                    name = subStreamItem[i].name,
                    color = null,
                    description = subStreamItem[i].description,
                )
            )
        }
        return sub
    }
}
