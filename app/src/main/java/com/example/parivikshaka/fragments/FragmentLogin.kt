package com.example.parivikshaka.fragments



/*  Code Written by Rajat Singh    */

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.parivikshaka.R
import com.example.parivikshaka.databinding.FragmentLoginBinding
import com.example.parivikshaka.db.ApiState
import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentLogin : Fragment() {

    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private val viewmodal: OtpLoginViewModel by viewModels()


    private val permissionID = 42

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.lifecycleOwner = requireActivity()
        binding.viewmodel = viewmodal
        binding.executePendingBindings()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodal.getMobileNumber().observe(requireActivity()) {
            if (it.length == 9) {
                binding.LoginBtn.background.setTint(Color.parseColor("#C54851"))
                binding.LoginBtn.setTextColor(Color.parseColor("#FFFFFF"))
                //binding.btnContinue.setBackgroundColor(R.color.btn_color)
                // binding.btnContinue.isEnabled = true

            } else {
                binding.LoginBtn.background.setTint(Color.parseColor("#f5f5f5"))
                binding.LoginBtn.setTextColor(Color.parseColor("#B3B3B3"))
                /*binding.btnContinue.setBackgroundColor(R.color.LightWhite)
                binding.btnContinue.isEnabled = false*/

            }
        }
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length!! >= 9) {
//                    binding.btnContinue.background.setTint(Color.parseColor("#D74F58"))
//                    binding.btnContinue.strokeWidth = 0
//                    binding.btnContinue.setTextColor(Color.parseColor("#FFFFFF"))
//                    binding.txt1.setStrokeColor(Color.parseColor("#FFFFFF"))
                    //binding.btnContinue.setBackgroundColor(R.color.btn_color)
                    // binding.btnContinue.isEnabled = true
                }
                else{
//                    binding.txt1.setStrokeColor(Color.parseColor("#CF6F76"))
                }
            }

        })


        binding.LoginBtn.setOnClickListener {
            callingOtpApi()
        }
//        requestPermissions()


    }

    private fun callingOtpApi() {
        lifecycleScope.launchWhenStarted {
            viewmodal.fetchingOtpApi(
                AuthRequest(
                    AuthUsername = "AgriDept",
                    AuthPassword = "AD@432!",
                    Username = binding.etEmail.text.toString(),
                    Password = binding.etPassword.text.toString()
                )
            ).collect { state ->
                when (state) {
                    is ApiState.Loading -> {
                        // Show loading indicator if needed
                        // binding.progressBar.visibility = View.VISIBLE
                    }

                    is ApiState.Success -> {

                        Log.d("API Response", "Success: ${state.data}")

                        when (state.data.Status?.toUpperCase()) {
                            "SUCCESS" -> {
                                val bundle = Bundle().apply {
                                    putString("Username", binding.etEmail.text.toString())
                                    putString("Password", binding.etPassword.text.toString())
                                    putString("MobileNo", binding.etEmail.text.toString())
                                    putString("userid", state.data.UserId)
                                }
                                findNavController().navigate(R.id.otpLoginToOtpSend, bundle)
                            }
                            "FAILURE" -> {
                                requireContext().showToast("User does not exist")
                                // Show error message if needed
                                // binding.tvError.visibility = View.VISIBLE
                                // binding.tvError.text = state.data.Message
                            }
                            else -> {
                                requireContext().showToast(state.data.Message ?: "Unknown error")
                            }
                        }
                    }

                    is ApiState.Failure -> {
                        // Hide loading indicator if needed
                        // binding.progressBar.visibility = View.GONE

                        Log.d("enterOtp", "callingOtpApi: ${state.msg}")
                        requireContext().showToast("Something went wrong")
                    }

                    is ApiState.Error -> {
                        // Hide loading indicator if needed
                        // binding.progressBar.visibility = View.GONE

                        Log.d("API Error", "Error: ${state.errorCode}")
                        requireContext().showToast("An error occurred")
                    }
                }
            }
        }
    }

    fun requestPermissions() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionID
        )
    }

}






































