package com.example.parivikshaka.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.parivikshaka.R
import com.example.parivikshaka.databinding.CardItemsBinding
import com.example.parivikshaka.models.SPTLDetailsList
import com.example.parivikshaka.models.TargetList

class CardItemAdapter: RecyclerView.Adapter<CardItemAdapter.ViewHolder>() {

    private var items: List<SPTLDetailsList> = ArrayList()


    // ViewHolder class to hold the view
    inner class ViewHolder(private val binding: CardItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        // Bind data to views
        fun bind(item: String) {
        binding.CardItems.text = item

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
        holder.bind(item.ApplicationName?:"")
        holder.itemView.setOnClickListener{
            val bundle = Bundle().apply {
                putString("ApplicationName", item.ApplicationName)
                putString("ApplicationConcern", item.ApplicationConcern)
                putString("Address", item.Address)
                putString("RegNo", item.RegNo)
            }
            it.findNavController().navigate(R.id.action_cardSampleFragment_to_targetDetailFragment, bundle)

        }

    }

    // Return item count
    override fun getItemCount(): Int {
        return items.size
    }

    // Update the items in the adapter
    fun updateItems(newItems: ArrayList<SPTLDetailsList>) {
        items = newItems
        notifyDataSetChanged()
    }

    // Add new items to the adapter

}