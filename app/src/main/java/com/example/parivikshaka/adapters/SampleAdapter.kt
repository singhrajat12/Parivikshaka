package com.example.parivikshaka.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parivikshaka.databinding.SampleItemsBinding
import com.example.parivikshaka.models.Sampledata
class SampleAdapter(private var items: MutableList<Sampledata> = mutableListOf(), private val listener: OnItemClickListener) : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Sampledata)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SampleItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: List<Sampledata>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<Sampledata>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: SampleItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Sampledata) {
            binding.AgentName.text = item.agentName.toString()
//            binding.textViewDescription.text = item.description
        }
    }
}
