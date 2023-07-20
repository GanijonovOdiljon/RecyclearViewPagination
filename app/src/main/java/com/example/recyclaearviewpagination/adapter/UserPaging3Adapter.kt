package com.example.recyclaearviewpagination.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclaearviewpagination.databinding.UserItemBinding
import com.example.recyclaearviewpagination.model.Data
import com.squareup.picasso.Picasso

class UserPaging3Adapter : PagingDataAdapter<Data, UserPaging3Adapter.DataVh>(MyDiffUtil()) {

    class MyDiffUtil : DiffUtil.ItemCallback<Data>() {

        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: DataVh, position: Int) {
        val item = getItem(position)
        if (item != null){
            holder.onBind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVh {
        return DataVh(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class DataVh(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Data) {
            binding.apply {
                Picasso.get()
                    .load(data.avatar).into(avatar)
                fullNameTv.text = data.first_name + " " + data.last_name
                emailTv.text = data.email
            }
        }
    }
}