package com.example.parivikshaka.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parivikshaka.R
import com.example.parivikshaka.databinding.FragmentCardSampleBinding
import com.example.parivikshaka.databinding.FragmentTargetDetailBinding

class TargetDetailFragment : Fragment() {
    private var applicationName: String? = null
    private var applicationConcern: String? = null
    private var address: String? = null
    private var regNo: String? = null
    private lateinit var binding: FragmentTargetDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTargetDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            applicationName = it.getString("ApplicationName")
            applicationConcern = it.getString("ApplicationConcern")
            address = it.getString("Address")
            regNo = it.getString("RegNo")
        }
        binding.applicationNameTextView.text="Reg No:$applicationName"
        binding.applicationConcernTextView.text= "Application Name:$applicationConcern"
        binding.addressTextView.text= "Address: $address"
        binding.regNoTextView.text= "Application Concern:$regNo"
    }
}