package com.uzlov.rentateam.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uzlov.rentateam.databinding.ItemUserLayoutBinding
import com.uzlov.rentateam.repo.models.Data

class UserAdapter(private val selectListener: UserSelectListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val users: MutableList<Data> = mutableListOf()
    private var _viewBinding: ItemUserLayoutBinding? = null
    private val viewBinding get() = _viewBinding!!

    interface UserSelectListener {
        fun userSelect(data: Data, adapterPosition: Int)
    }

    fun setUsers(_users: List<Data>) {
        users.apply {
            clear()
            addAll(_users)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _viewBinding = ItemUserLayoutBinding.inflate(inflater, parent, false)
        return UserViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(users[position])
    }

    override fun getItemCount(): Int = users.size


    inner class UserViewHolder(private val binding: ItemUserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Data) {
            with(binding) {
                tvNameUser.text = data.first_name
                tvLastnameUser.text = data.last_name
                root.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION){
                        selectListener.userSelect(data, adapterPosition)
                    }
                }
            }
        }
    }
}