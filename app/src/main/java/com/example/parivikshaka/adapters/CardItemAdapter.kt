package com.example.parivikshaka.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parivikshaka.databinding.CardItemsBinding
import com.example.parivikshaka.fragments.CardSampleFragment

class CardItemAdapter: RecyclerView.Adapter<CardItemAdapter.ViewHolder>() {

    private var items: List<String> = ArrayList()


    // ViewHolder class to hold the view
    inner class ViewHolder(private val binding: CardItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        // Bind data to views
        fun bind(item: String) {
        binding.CardItems.text = item
            binding.CardItems.setOnClickListener{


            }
        }
    }

    // Create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Bind data to ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    // Return item count
    override fun getItemCount(): Int {
        return items.size
    }

    // Update the items in the adapter
    fun updateItems(newItems: List<String>) {
        items = newItems
        notifyDataSetChanged()
    }

    // Add new items to the adapter
    fun addItems(newItems: List<String>) {
        items += newItems
        notifyDataSetChanged()
    }
}