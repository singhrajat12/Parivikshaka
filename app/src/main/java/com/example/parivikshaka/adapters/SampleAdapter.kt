package com.example.parivikshaka.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.parivikshaka.R
import com.example.parivikshaka.databinding.SampleItemsBinding
import com.example.parivikshaka.models.Sampledata


 class SampleAdapter: ListAdapter<Sampledata, SampleAdapter.ViewHolder>(DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
     val inflater = LayoutInflater.from(parent.context)
        val binding = SampleItemsBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val Item = getItem(position)
        holder.bind(Item)

    }

   class ViewHolder(private val binding: SampleItemsBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Sampledata) {
          binding.item = item
            binding.executePendingBindings()

        }
    }

     class DiffCallback: DiffUtil.ItemCallback<Sampledata>(){
         override fun areItemsTheSame(oldItem: Sampledata, newItem: Sampledata): Boolean {
             return oldItem.id == newItem.id
         }

         override fun areContentsTheSame(oldItem: Sampledata, newItem: Sampledata): Boolean {
            return oldItem == newItem
         }

     }


}
