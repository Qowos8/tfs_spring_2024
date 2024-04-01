package com.example.homework_2.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homework_2.R
import com.example.homework_2.databinding.PeopleComponentRecyclerBinding

class PeopleAdapter(
    private val onItemClick: (PeopleItem) -> Unit,
) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private var usersList: MutableList<PeopleItem> = mutableListOf()

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
        private val onItemClick: (PeopleItem) -> Unit,
    ) : ViewHolder(binding.root) {
        fun bind(item: PeopleItem) {
            binding.apply {
                userName.text = item.name
                email.text = item.mail
                if(!item.isOnline){
                    userStatusCircle.setImageResource(R.drawable.offline)
                }
                root.setOnClickListener {
                    (onItemClick(item))
                }
            }
        }
    }

    fun update(newList: MutableList<PeopleItem>) {
        usersList = newList
        notifyDataSetChanged()
    }

}