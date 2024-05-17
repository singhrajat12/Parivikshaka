package com.example.parivikshaka.fragments


import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        requestPermissions()


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
                when(state){
                    is  ApiState.Loading ->{
//                        binding.progressBar.visibility=View.VISIBLE
                    }


                    is ApiState.Success ->{
//                        binding.progressBar.visibility=View.GONE
                        when (state.data.Status) {
                            true.toString() -> {
                                Bundle().let {
                                    it.putString("Username" , binding.etEmail.text.toString())
                                    it.putString("Password" , binding.etPassword.text.toString())
                                    findNavController().navigate(R.id.otpLoginToOtpSend , it)
                                }

                            }
                            false.toString() -> {
                                //   findNavController().navigate(R.id.otpLoginToOtpSend)
                                requireContext().showToast("User not exist")

//                                binding.tvError.visibility = View.VISIBLE
                                //  binding.tvError.text = state.data.data.res?.response.toString()
                            }
                            else -> {
                                requireContext().showToast("${state.data.Message.toString()}")
                            }

                        }

                    }
                    is ApiState.Failure ->{
//                        binding.progressBar.visibility=View.GONE
                        Log.d("enterOtp", "callingOtpApi: $state")
                        requireContext().showToast("Something went Wrong")
                    }

                    is ApiState.Error -> {

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



















//    private var _binding: FragmentLoginBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var onBackPressedCallback: OnBackPressedCallback
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
//        onBackPressedCallback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                // Leave this method empty to disable the back press
//            }
//        }
//
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
//
//
//        binding.LoginBtn.setOnClickListener {
//            findNavController().navigate(R.id.otpLoginToOtpSend)
//        }
//
//
//    }
//
//
//
//}









//
//@AndroidEntryPoint
//class FragmentLogin : Fragment() {
//    private var _binding: FragmentLoginBinding? = null
//    private val binding get() = _binding!!
//    private val viewModel: OtpLoginViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentLoginBinding.inflate(inflater, container, false)
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = viewLifecycleOwner
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Disable the back press
//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                // Leave this method empty to disable the back press
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
//
//        // Set input filter and TextWatcher for the mobile number EditText
//        binding.etEmail.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))
//        binding.etEmail.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                // No action needed after text change
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // No action needed before text change
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s?.length ?: 0 > 10) {
//                    binding.etEmail.setText(s?.substring(0, 10))
//                    binding.etEmail.setSelection(10)
//                }
//            }
//        })
//
//        // Observe the login result
////        viewModel.loginResult.observe(viewLifecycleOwner, Observer { state ->
////            when (state) {
////                is ApiState.Success -> {
////                    if (state.data.status == "success") {
////                        findNavController().navigate(R.id.otpLoginToOtpSend)
////                    } else {
////                        // Handle the failure status
////                        // Show the status message
////                        requireContext().showToast("User not exist")
////
////                    }
////                }
////                is ApiState.Failure -> {
////                    // Handle the error
//////                    binding.statusMessage.text = "Login failed: ${state.error}"
////                }
////                else -> {}
////            }
////        })
////
////        // Set click listener for the login button
////        binding.LoginBtn.setOnClickListener {
////            val username = binding.etEmail.text.toString()
////            val password = binding.etPassword.text.toString()
////            val authRequest = AuthRequest(username, password, "authUsername", "authPassword")
////            viewModel.login(authRequest)
////        }
//
//
//
//
//
//    }
//
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
























