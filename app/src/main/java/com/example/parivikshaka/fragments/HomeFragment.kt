package com.example.parivikshaka.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.parivikshaka.R
import com.example.parivikshaka.databinding.FragmentHomeBinding
import com.example.parivikshaka.databinding.FragmentOtpEnterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var DistrictId: Int? = null
    private var FinancialYearID: Int? = null
    private var HobliId: Int? = null
    private var RoleID: Int? = null
    private var TalukId: Int? = null
    private var TypeId: Int? = null
    private var Username:String?=null
    private var Password:String?=null

    companion object {
        fun newInstance() = HomeFragment()
    }
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Set up the onBackPressedCallback to intercept the back button press
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Leave this method empty to disable the back press
            }
        }

         binding.AssignedCard.setOnClickListener {
             findNavController().navigate(R.id.action_fragment_home_to_SampleFragment)
         }



        binding.clothingCard.setOnClickListener {

            findNavController().navigate(R.id.action_fragment_home_to_cardSampleFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove() // Remove the onBackPressedCallback when the view is destroyed
    }




}