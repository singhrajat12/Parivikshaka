package com.example.parivikshaka.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parivikshaka.adapters.SampleAdapter
import com.example.parivikshaka.databinding.FragmentSampleBinding
import com.example.parivikshaka.models.Sampledata
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SampleFragment : Fragment() {
    private val binding by lazy{
        FragmentSampleBinding.inflate(layoutInflater)
    }

    private val viewModel : SampleViewModel by viewModels()
    private val sampleadapter = SampleAdapter()
    private lateinit var datList: List<Sampledata>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.fetchSampleItems()

        viewModel.sampleItems.observe(viewLifecycleOwner) { items ->
            sampleadapter.submitList(items)
        }

        onClick()

    }

    private fun onClick() {
        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }

private fun setupRecyclerView(){
    binding.viewTicketRecyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = sampleadapter
    }

}


}
