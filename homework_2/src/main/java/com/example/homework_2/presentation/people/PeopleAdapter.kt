package com.example.homework_2.presentation.people

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.homework_2.R
import com.example.homework_2.data.network.model.event.PresenceResponse
import com.example.homework_2.databinding.PeopleComponentRecyclerBinding
import com.example.homework_2.domain.entity.ProfileItem

class PeopleAdapter(
    private val onItemClick: (ProfileItem) -> Unit,
) : ListAdapter<ProfileItem, PeopleAdapter.PeopleViewHolder>(PeopleDiffUtil()) {

    private var statusList: PresenceResponse? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PeopleComponentRecyclerBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = currentList.size

    inner class PeopleViewHolder(
        private val binding: PeopleComponentRecyclerBinding,
        private val onItemClick: (ProfileItem) -> Unit,
    ) : ViewHolder(binding.root) {
        fun bind(item: ProfileItem) {
            binding.apply {
                userName.text = item.name
                email.text = item.email
                if (statusList != null) {
                    if (statusList!!.presences[layoutPosition].email.toString() == item.email) {
                        if (statusList!!.presences[layoutPosition].email.status == ONLINE) {
                            userStatusCircle.setImageResource(R.drawable.online)
                        } else if (statusList!!.presences[layoutPosition].email.timestamp - statusList!!.serverTimestamp > 600) {
                            userStatusCircle.setImageResource(R.drawable.offline)
                        } else {
                            userStatusCircle.setImageResource(R.drawable.idle)
                        }
                    }
                } else {
                    if (!item.isActive) {
                        userStatusCircle.setImageResource(R.drawable.offline)
                    } else {
                        userStatusCircle.setImageResource(R.drawable.online)
                    }
                }

                if (item.url == null) {
                    circleAvatar.apply {
//                        circleCardImage.isVisible = true
//                        circleCardImagePlaceholder.isVisible = true
                        cardImage.isVisible = true
                        cardImage.setImageResource(R.drawable.baseline_account_circle_24)
                    }
                } else {
                    circleAvatar.apply {
                        cardImage.isVisible = true
//                        circleCardImagePlaceholder.isVisible = false
//                        circleCardImage.isVisible = false
                        val desiredWidthPx =
                            (55 * root.context.resources.displayMetrics.density + 0.5f).toInt()
                        Glide.with(cardImage)
                            .load(item.url)
                            .placeholder(R.drawable.baseline_account_circle_24)
                            .override(desiredWidthPx, desiredWidthPx)
                            .into(cardImage)
                    }
                }
                root.setOnClickListener {
                    (onItemClick(item))
                }
            }
        }
    }

    private companion object {
        private const val ONLINE = "active"
        private const val IDLE = "idle"
        private const val OFFLINE = "offline"
    }
}