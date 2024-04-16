package com.example.homework_2.utils

import com.example.homework_2.channels.AllStreamItem
import com.example.homework_2.channels.SubStreamItem

object MapperAllToSub{
    fun mapAllToSub(subStreamItem: List<SubStreamItem>): List<AllStreamItem> {
        val sub: MutableList<AllStreamItem> = mutableListOf()
        for (i in 0..subStreamItem.size) {
            sub.add(
                AllStreamItem(
                    id = subStreamItem[i].streamId,
                    name = subStreamItem[i].name,
                    color = null, // Предполагаем, что это поле всегда null для объектов типа AllStreamItem
                    description = subStreamItem[i].description,
//                    audibleNotifications = null, // Предполагаем, что это поле всегда null для объектов типа AllStreamItem
//                    desktopNotifications = null, // Предполагаем, что это поле всегда null для объектов типа AllStreamItem
//                    inviteOnly = subStreamItem[i].inviteOnly,
//                    isMuted = false, // Предполагаем, что это поле всегда false для объектов типа AllStreamItem
//                    pinToTop = false, // Предполагаем, что это поле всегда false для объектов типа AllStreamItem
//                    pushNotifications = null, // Предполагаем, что это поле всегда null для объектов типа AllStreamItem
//                    subscribers = emptyList(),
                )
            )
        }
        return sub
    }
}
