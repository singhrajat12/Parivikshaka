package com.example.parivikshaka.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.parivikshaka.MainActivity
import com.example.parivikshaka.R
import com.example.parivikshaka.SharedPreferenceCommon
import com.example.parivikshaka.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class FragmentSplash : Fragment() {
    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    @Inject
    lateinit var sharedPreferenceCommon: SharedPreferenceCommon

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize sharedPreferenceCommon if it is not initialized
        if (!::sharedPreferenceCommon.isInitialized) {
            sharedPreferenceCommon = SharedPreferenceCommon(requireContext())
        }

        // Use viewLifecycleOwner.lifecycleScope for coroutine scope
        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000)


            // Check if the fragment is attached before navigating
            if (isAdded && activity != null) {
                findNavController().navigate(R.id.action_splash_to_fragment_otp_login)
            } else if (sharedPreferenceCommon.gettoken().isNotEmpty()) {

                findNavController().navigate(R.id.splashToHome)
            } else {
                if (sharedPreferenceCommon.getOneTimeInstall()) {


                    findNavController().navigate(R.id.action_splash_to_fragment_otp_login)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear binding to avoid memory leaks
//        _binding = null
    }
}
