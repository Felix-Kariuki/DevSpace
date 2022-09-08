package com.flexcode.devspace.wakatime.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flexcode.devspace.databinding.LayoutEditorBinding
import com.flexcode.devspace.wakatime.domain.models.Editors

class EditorsAdapter : ListAdapter<Editors, EditorsAdapter.MyViewHolder>(COMPARATOR) {

    inner class MyViewHolder(private val binding: LayoutEditorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(editors: Editors) {
            binding.apply {
                if (editors.released == true) tvEditorAvailability.text =
                    "Available" else tvEditorAvailability.isGone = true
                tvEditorName.text = editors.name
                tvEditorVersion.text = "Version: ${editors.version}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutEditorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val editors = getItem(position)
        holder.bind(editors)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Editors>() {
            override fun areItemsTheSame(oldItem: Editors, newItem: Editors): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Editors, newItem: Editors): Boolean {
                return oldItem == newItem
            }
        }
    }
}