package com.example.parivikshaka.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parivikshaka.adapters.SampleAdapter
import com.example.parivikshaka.databinding.FragmentSampleBinding
import com.example.parivikshaka.models.Sampledata

class SampleFragment : Fragment(), SampleAdapter.OnItemClickListener {

    private lateinit var binding: FragmentSampleBinding
    private lateinit var itemsAdapter: SampleAdapter
    private var currentPage = 1
    private val pageSize = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchCardItems()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        itemsAdapter = SampleAdapter(listener = this)
        binding.viewTicketRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {
                        fetchNextPage()
                    }
                }
            })
        }
    }

    private fun fetchCardItems() {
        val cardItems = getCardItemsFromRepository(currentPage)
        itemsAdapter.updateItems(cardItems)
    }

    private fun fetchNextPage() {
        currentPage++
        val cardItems = getCardItemsFromRepository(currentPage)
        itemsAdapter.addItems(cardItems)
    }

    private fun getCardItemsFromRepository(page: Int): List<Sampledata> {
        val sampleDataList = mutableListOf<Sampledata>()
        val startIndex = (page - 1) * pageSize + 1
        val endIndex = startIndex + pageSize - 1
        for (i in startIndex..endIndex) {
            // Create Sampledata with actual data or null for optional parameters
            sampleDataList.add(Sampledata("$i", "Agent $i", "Assignment $i"))
        }
        return sampleDataList
    }


//    override fun onItemClick(item: CardSampleData) {
//        findNavController().navigate(R.id.action_cardSampleFragment_to_fragment_Sample)
//    }

    private fun setupClickListeners() {
        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onItemClick(item: Sampledata) {

    }


}
