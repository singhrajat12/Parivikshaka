package com.example.parivikshaka.fragments

import android.R
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parivikshaka.databinding.FragmentSampleBinding
import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.models.DistrictList
import com.example.parivikshaka.models.HobliList
import com.example.parivikshaka.models.TalukList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleFragment:Fragment() {

    val binding by lazy {
        FragmentSampleBinding.inflate(layoutInflater).apply {
            viewModel = model
        }
    }
    var districtNames:List<String> = emptyList();
    var districtid: Int?=-1
    val model:SampleViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.lifecycleOwner = requireActivity()
        binding.viewModel = model
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.districtList.observe(viewLifecycleOwner, Observer { districts ->
             districtNames = districts.map { it.DistrictName ?: "" } // Assuming DistrictName is a property of DistrictList
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, districtNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.DistrictType.adapter = adapter
            binding.DistrictType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    // Fetch districts with the provided username and password
                    districtid=districts.get(position).DistrictId?:-1
                    model.fetchTaluks("AgriDept", "AD@432!",districts.get(position).DistrictId?:-1)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(requireContext(), "No district selected", Toast.LENGTH_SHORT).show()
                }
            }
        })

// Observe error LiveData to show error messages
        model.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        })

// Set OnItemSelectedListener to fetch districts when an item is selected




        model.talukList.observe(viewLifecycleOwner, Observer { taluks ->
            val talukNames = taluks.map { it.TalukName ?: "" }
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, talukNames)
            binding.TalukType.adapter = adapter
            binding.TalukType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {


                    // Call the ViewModel method to fetch Taluks based on the selected DistrictId
                    model.fetchHobli("AgriDept", "AD@432!", districtid?:-1, talukId = taluks.get(position).TalukId?:-1)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(requireContext(), "No Taluck selected", Toast.LENGTH_SHORT).show()
                }
            }
        })

        model.error.observe(viewLifecycleOwner, Observer { errorMessage ->

            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        })






//        model.fetchTaluks("AgriDept", "AD@432!" )


        model.hobliList.observe(viewLifecycleOwner, Observer { hoblis ->
            val hobliNames = hoblis.map { it.HobliName ?: "" }
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, hobliNames)
            binding.Hobliype.adapter = adapter
            binding.Hobliype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(requireContext(), "No Taluck selected", Toast.LENGTH_SHORT).show()
                }
            }
        })

        model.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        })



    }
}






//    private fun onClick() {
//
////        binding.arrowBack.setOnClickListener {
////            findNavController().popBackStack()
////        }
//
//    }

    var watcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //YOUR CODE
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //YOUR CODE
        }

        override fun afterTextChanged(s: Editable) {


        }
    }
