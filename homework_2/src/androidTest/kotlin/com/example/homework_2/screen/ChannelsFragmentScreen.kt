package com.example.homework_2.screen

import com.example.homework_2.R
import com.example.homework_2.presentation.channels.child.ChannelsFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher
import android.view.View

object ChannelsFragmentScreen: KScreen<ChannelsFragmentScreen>() {
    override val layoutId: Int = R.layout.expandable_fragment
    override val viewClass: Class<*> = ChannelsFragment::class.java

    val recyсlerStream = KRecyclerView(
        builder = { withId(R.id.parent_recycler)},
        itemTypeBuilder = {
            itemType(::KStream)
        }
    )

    val recyclerTopic = KRecyclerView(
        builder = { withId(R.id.parent_recycler)},
        itemTypeBuilder = {
            itemType(::KTopic)
        }
    )

    class KStream(parent: Matcher<View>) : KRecyclerItem<KStream>(parent) {
        val name = KTextView { withId(R.id.name_stream) }
    }

    class KTopic(parent: Matcher<View>) : KRecyclerItem<KTopic>(parent) {
        val name = KTextView { withId(R.id.name_topic) }
    }


}