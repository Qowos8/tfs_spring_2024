package com.example.homework_2.people

import android.app.ActionBar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.homework_2.R
import com.example.homework_2.databinding.PeopleComponentRecyclerBinding
import com.example.homework_2.databinding.ProfileCircleCardBinding
import com.example.homework_2.profile.ProfileItem

class PeopleAdapter(
    private val onItemClick: (ProfileItem) -> Unit,
) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private var usersList: List<ProfileItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PeopleComponentRecyclerBinding.inflate(inflater, parent, false)
        val cardBinding = ProfileCircleCardBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding,cardBinding, onItemClick)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val user = usersList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = usersList.size

    inner class PeopleViewHolder(
        private val binding: PeopleComponentRecyclerBinding,
        private val cardBinding: ProfileCircleCardBinding,
        private val onItemClick: (ProfileItem) -> Unit,
    ) : ViewHolder(binding.root) {
        fun bind(item: ProfileItem) {
            binding.apply {
                userName.text = item.name
                if(email != null){
                    email.text = item.email
                }
                if(!item.isActive){
                    userStatusCircle.setImageResource(R.drawable.offline)
                }
                else{
                    userStatusCircle.setImageResource(R.drawable.online)
                }
                if(item.url == null){
                    cardBinding.circleCardImage.isVisible = false
                    cardBinding.mockAvatar.isVisible = true
                }
                else{
                    Glide.with(cardBinding.circleCardImage)
                        .load(item.url)
                        .override(Target.SIZE_ORIGINAL)
                        .override(ActionBar.LayoutParams.MATCH_PARENT)
                        .into(cardBinding.circleCardImage)
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

}