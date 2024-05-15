package com.example.homework_2.screen

import android.view.View
import com.example.homework_2.R
import com.example.homework_2.presentation.chat.ChatActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.github.kakaocup.kakao.toolbar.KToolbar
import org.hamcrest.Matcher

object ChatActivityScreen: KScreen<ChatActivityScreen>() {
    override val layoutId = R.layout.chat_activity
    override val viewClass = ChatActivity::class.java

    val sendButton = KButton {withId(R.id.message_button)}

    val topicName = KTextView {withId(R.id.topic_name)}
    val streamName = KToolbar{withId(R.id.toolbar)}
    val recycler = KRecyclerView(
        builder = { withId(R.id.message_recycler)},
        itemTypeBuilder = {
            itemType(::MessengerLayoutItem)
            itemType(::CompanionMessengerLayoutItem)
        }
    )

    class MessengerLayoutItem(parent: Matcher<View>) : KRecyclerItem<MessengerLayoutItem>(parent) {
        val messageRoot = KView { withId(R.id.messenger_layout) }
        val avatarImageView = KImageView { withId(R.id.avatar) }
        val messageTextView = KTextView { withId(R.id.messageTextView) }
        val addButtonImageView = KImageView { withId(R.id.add_button) }
    }

    class CompanionMessengerLayoutItem(parent: Matcher<View>) : KRecyclerItem<CompanionMessengerLayoutItem>(parent) {
        val messageRoot = KView { withId(R.id.messenger_layout) }
        val avatarImageView = KImageView { withId(R.id.avatar) }
        val userNameTextView = KTextView { withId(R.id.userNameTextView) }
        val messageTextView = KTextView { withId(R.id.companion_messageTextView) }
        val addButtonImageView = KImageView { withId(R.id.add_button) }
    }

}