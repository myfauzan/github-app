package com.myfauzan.githubapp2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myfauzan.githubapp2.databinding.ItemRowUserBinding


class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val listUser = ArrayList<User>()
    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users: List<User>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemRowUserBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }
            binding.apply {
                tvUsername.text = user.login
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .circleCrop()
                    .into(ivAvatar)

            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRowUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}