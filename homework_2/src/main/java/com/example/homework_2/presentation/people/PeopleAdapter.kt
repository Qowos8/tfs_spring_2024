package com.example.homework_2.presentation.people

import android.app.ActionBar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.homework_2.R
import com.example.homework_2.data.network.model.event.PresenceResponse
import com.example.homework_2.databinding.PeopleComponentRecyclerBinding
import com.example.homework_2.data.network.model.profile.ProfileItemApi
import com.example.homework_2.domain.entity.ProfileItem

class PeopleAdapter(
    private val onItemClick: (ProfileItem) -> Unit,
) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private var usersList: List<ProfileItem> = mutableListOf()
    private var statusList: PresenceResponse? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PeopleComponentRecyclerBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val user = usersList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = usersList.size

    inner class PeopleViewHolder(
        private val binding: PeopleComponentRecyclerBinding,
        private val onItemClick: (ProfileItem) -> Unit,
    ) : ViewHolder(binding.root) {
        fun bind(item: ProfileItem) {
            binding.apply {
                userName.text = item.name
                email.text = item.email
                if(statusList != null){
                    if(statusList!!.presences[layoutPosition].email.toString() == item.email){
                        if(statusList!!.presences[layoutPosition].email.status == ONLINE){
                            userStatusCircle.setImageResource(R.drawable.online)
                        }
                        else if(statusList!!.presences[layoutPosition].email.timestamp - statusList!!.serverTimestamp > 600){
                            userStatusCircle.setImageResource(R.drawable.offline)
                        }
                        else{
                            userStatusCircle.setImageResource(R.drawable.idle)
                        }
                    }
                }
                else{
                    if(!item.isActive){
                        userStatusCircle.setImageResource(R.drawable.offline)
                    }
                    else{
                        userStatusCircle.setImageResource(R.drawable.online)
                    }
                }

                if(item.url == null){
                    circleAvatar.circleCardImage.isVisible = true
                    circleAvatar.cardImage.isVisible = false
                }
                else{
                    circleAvatar.cardImage.isVisible = true
                    circleAvatar.circleCardImage.isVisible = false
                    Glide.with(circleAvatar.cardImage)
                        .load(item.url)
                        .override(Target.SIZE_ORIGINAL)
                        .override(ActionBar.LayoutParams.MATCH_PARENT)
                        .into(circleAvatar.cardImage)
                }
                root.setOnClickListener {
                    (onItemClick(item))
                }
            }
        }
    }

    fun update(newList: List<ProfileItem>) {
        usersList = newList
        notifyDataSetChanged()
    }

    fun setStatus(status: PresenceResponse){
        statusList = status
        notifyDataSetChanged()
    }
    
    private companion object{
        private const val ONLINE = "active"
        private const val IDLE = "idle"
        private const val OFFLINE = "offline"
    }
}