package com.example.brainydxassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brainydxassignment.data.remote.model.ModelClassItem
import com.example.brainydxassignment.data.remote.model.Verification
import com.example.brainydxassignment.databinding.ListItemBinding

class UserAdpater(
    private val context: Context,
    private val users: ArrayList<ModelClassItem>
): RecyclerView.Adapter<UserAdpater.UserViewholder>() {


    inner class UserViewholder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewholder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return UserViewholder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewholder, position: Int) {
        holder.binding.textView2.text = "Login id: "+users.get(position).author.login
        holder.binding.textView5.text = "Committer: "+users.get(position).commit.committer.name
        holder.binding.textView7.text = "Message: "+users.get(position).commit.message
    }

}