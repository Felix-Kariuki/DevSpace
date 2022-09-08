package com.flexcode.devspace.github.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexcode.devspace.R
import com.flexcode.devspace.databinding.LayoutRepositoryBinding
import com.flexcode.devspace.github.domain.model.Repository

class RepositoriesAdapter(private val onClickListener  : OnClickListener) :
    ListAdapter<Repository,RepositoriesAdapter.MyViewHolder>(COMPARATOR){

        inner class MyViewHolder(private val binding : LayoutRepositoryBinding) :
                RecyclerView.ViewHolder(binding.root){
                    fun bind(repo:Repository?){
                        binding.apply {
//                            ivRepoOwner.load(repo?.owner?.avatar_url){
//                                placeholder(R.drawable.ic_placeholder)
//                                crossfade(true)
//                                transformations(CircleCropTransformation())
//                            }
                            Glide.with(binding.root)
                                .load(repo?.owner?.avatar_url)
                                .placeholder(R.drawable.ic_placeholder)
                                .into(ivRepoOwner)

                            tvRepoDescription.text = repo?.description
                            tvRepoName.text = repo?.name
                            tvRepoLanguage.text = repo?.language
                            tvRepoStar.text = repo?.stargazers_count.toString()
                            /**
                             * get login to owner to display as name
                             */
                        }
                    }
                }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(repo)
        }
    }


    companion object{
        private val COMPARATOR = object :DiffUtil.ItemCallback<Repository>(){
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem == newItem
            }
        }
    }

    class OnClickListener(val clickListener: (repo: Repository) -> Unit) {
        fun onClick(repo: Repository) = clickListener(repo)
    }
}