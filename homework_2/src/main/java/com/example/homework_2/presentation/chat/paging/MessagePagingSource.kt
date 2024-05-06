package com.example.homework_2.presentation.chat.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework_2.domain.entity.MessageItem

class MessagePagingSource(
    private val messages: List<MessageItem>,
): PagingSource<Int, MessageItem>() {

    override fun getRefreshKey(state: PagingState<Int, MessageItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MessageItem> {
        val pageNumber = params.key ?: 0
        val pageSize = params.loadSize
        return LoadResult.Page(
            data = messages,
            prevKey = if(pageNumber > 1) pageNumber - 1 else null,
            nextKey = if (messages.isNotEmpty()) pageNumber + 1 else null
        )
    }
}