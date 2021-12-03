package com.example.githubrepolist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepolist.databinding.CardviewListItemBinding
import com.example.githubrepolist.network.GitHubRepo
import java.text.SimpleDateFormat

class ItemAdapter:
    ListAdapter<GitHubRepo, ItemAdapter.ItemViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<GitHubRepo>() {
        override fun areItemsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ItemViewHolder(
        private var binding: CardviewListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gitHubRepo: GitHubRepo){
            binding.name.text=gitHubRepo.name
            binding.description.text=gitHubRepo.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(
            CardviewListItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
}