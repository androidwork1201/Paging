package com.example.pagingdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingdemo.R
import com.example.pagingdemo.databinding.LayoutItemBinding

import com.example.pagingdemo.model.Repo

/**加入DiffUtil管理數據變化並使用viewBinding簡化綁定元件步驟*/

class RepoAdapter : PagingDataAdapter<Repo, RepoAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.name == newItem.name &&
                       oldItem.count == newItem.count &&
                       oldItem.description == newItem.description
            }

        }
    }

    class ViewHolder(val binding: LayoutItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val repo = getItem(position)
            if (repo != null) {
                binding.nameText.text = repo.name
                binding.descriptionText.text = repo.description
                binding.iconCountText.text = repo.count.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}