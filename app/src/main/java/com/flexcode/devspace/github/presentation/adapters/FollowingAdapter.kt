package com.flexcode.devspace.github.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexcode.devspace.R
import com.flexcode.devspace.databinding.LayoutFollowersBinding
import com.flexcode.devspace.github.domain.model.Following

class FollowingAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Following, FollowingAdapter.MyViewHolder>(COMPARATOR) {

    inner class MyViewHolder(private val binding: LayoutFollowersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: Following?) {
            binding.apply {
//                        ivFollowerImage.load("${follower?.avatar_url}"){
//                            crossfade(true)
//                            placeholder(R.drawable.ic_placeholder)
//                            transformations(CircleCropTransformation())
//                        }
                Glide.with(binding.root)
                    .load(follower?.avatar_url)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(ivFollowerImage)
                tvFollowerUserName.text = follower?.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutFollowersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val follower = getItem(position)
        holder.bind(follower)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(follower)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Following>() {
            override fun areItemsTheSame(oldItem: Following, newItem: Following): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Following, newItem: Following): Boolean {
                return oldItem == newItem
            }
        }
    }

    class OnClickListener(val clickListener: (follower: Following) -> Unit) {
        fun onClick(follower: Following) = clickListener(follower)
    }
}
