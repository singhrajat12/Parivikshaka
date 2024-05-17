package com.example.parivikshaka.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.parivikshaka.R
import com.example.parivikshaka.databinding.FragmentLoginBinding
import com.example.parivikshaka.databinding.FragmentOtpEnterBinding
import com.example.parivikshaka.db.ApiState

import com.example.parivikshaka.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentEnterOTP : Fragment() {

    companion object {
        fun newInstance() = FragmentEnterOTP()
    }

    private var _binding: FragmentOtpEnterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FragmentEnterOTViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpEnterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_otp_enter_to_fragment_home)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.otpnter_otp_login)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



//@AndroidEntryPoint
//class FragmentEnterOTP : Fragment() {
//
//    private var _binding: FragmentLoginBinding? = null
//    private val binding get() = _binding!!
//    private val viewModel: OtpLoginViewModel by viewModels()
//
//    private val permissionID = 42
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentLoginBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.getMobileNumber().observe(viewLifecycleOwner) {
//            if (it.length == 9) {
//                binding.LoginBtn.background.setTint(Color.parseColor("#C54851"))
//                binding.LoginBtn.setTextColor(Color.parseColor("#FFFFFF"))
//            } else {
//                binding.LoginBtn.background.setTint(Color.parseColor("#f5f5f5"))
//                binding.LoginBtn.setTextColor(Color.parseColor("#B3B3B3"))
//            }
//        }
//
//        binding.etEmail.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//            override fun afterTextChanged(s: Editable?) {}
//        })
//
//        binding.LoginBtn.setOnClickListener {
//            callingOtpApi()
//        }
//
//        requestPermissionsIfNeeded()
//    }
//
//    private fun callingOtpApi() {
//        val username = binding.etEmail.text.toString()
//        val password = binding.etPassword.text.toString()
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.fetchingOtpApi(
//                AuthRequest(
//                    username = username,
//                    password = password,
//                    authUsername = username,
//                    authPassword = password
//                )
//            ).collect { state ->
//                when (state) {
//                    is ApiState.Loading -> {
//                        // Show loading progress if needed
//                    }
//                    is ApiState.Success<User> -> {
//                        val user = state.data
//                        if (user) {
//                            val bundle = Bundle().apply {
//                                putString("UserName", username)
//                                putString("Password", password)
//                            }
//                            findNavController().navigate(R.id.otpLoginToOtpSend, bundle)
//                        } else {
//                            requireContext().showToast("User not exist")
//                        }
//                    }
//                    is ApiState.Failure -> {
//                        requireContext().showToast("Something went Wrong")
//                    }
//                    is ApiState.Error -> {
//                        // Handle error state if needed
//                    }
//                }
//            }
//        }
//    }
//
//    private fun requestPermissionsIfNeeded() {
//        if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED ||
//            ContextCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            requestPermissions(
//                arrayOf(
//                    Manifest.permission.ACCESS_COARSE_LOCATION,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ),
//                permissionID
//            )
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == permissionID) {
//            // Handle permission result if needed
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}