package com.example.parivikshaka.fragments

import android.graphics.Color
import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.parivikshaka.R
import com.example.parivikshaka.databinding.FragmentOtpEnterBinding
import com.example.parivikshaka.db.ApiState
import com.example.parivikshaka.models.AuthRequest
import com.example.parivikshaka.models.VerifyOtpRequest
import com.example.parivikshaka.models.VerifyOtpResponse
import com.example.parivikshaka.utils.configOtpEditText
import com.example.parivikshaka.utils.showToast

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentEnterOTP : Fragment() {

    private val mBinding by lazy { FragmentOtpEnterBinding.inflate(layoutInflater) }
    private val mViewModal: FragmentEnterOTViewModel by viewModels()
    private val viewmodal: OtpLoginViewModel by viewModels()
    private var number: String? = null
    private var UserId: String? = null
    private var Username:String?=null
    private var Password:String?=null
    var otpget:String?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding.lifecycleOwner = requireActivity()
        mBinding.viewmodel = mViewModal
        mBinding.executePendingBindings()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            number = it.getString("MobileNo")
            mBinding.otpTxt.text = "Enter the 4 digit OTP sent to you at $number"
        }
        mBinding.backButton.setOnClickListener {
            findNavController().navigate(R.id.otpnter_otp_login)
        }
        configOtpEditText(mBinding.inputCode1, mBinding.inputCode2, mBinding.inputCode3, mBinding.inputCode4)
        apiCallVerifyOtp()
        mBinding.btnContinue.setOnClickListener {
            if (verifyEnteredOtp()) {
                val ombinedotp:String=mBinding.inputCode1.text.toString()+""+
                        mBinding.inputCode2.text.toString()+""+
                        mBinding.inputCode3.text.toString()+""+
                        mBinding.inputCode4.text
                if(ombinedotp.equals(otpget)){
                    findNavController().navigate(R.id.action_fragment_otp_enter_to_fragment_home)

                }else{
                    requireContext().showToast("Invalid OTP ")
                }

            }
        }
        onClick()
        countDownTimer()
    }

    private fun onClick() {
        mBinding.inputCode1.addTextChangedListener(watcher)
        mBinding.inputCode2.addTextChangedListener(watcher)
        mBinding.inputCode3.addTextChangedListener(watcher)
        mBinding.inputCode4.addTextChangedListener(watcher)
    }

    private fun countDownTimer() {
        object : CountDownTimer(57000, 1000) {
            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                mBinding.TvOTP.text = "Didn’t receive OTP? Request for a new one in ${millisUntilFinished / 1000} seconds"
            }

            // Callback function, fired when the time is up
            override fun onFinish() {
                mBinding.TvOTP.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(getString(R.string.resendOtp), Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(getString(R.string.resendOtp))
                }

                mBinding.TvOTP.setOnClickListener {
                    resendOtp()
                    countDownTimer()
                }
            }
        }.start()
    }

    private fun resendOtp() {
        lifecycleScope.launchWhenStarted {
            viewmodal.fetchingOtpApi(
                AuthRequest(
                    AuthUsername = "AgriDept",
                    AuthPassword = "AD@432!",
                    Username = Username,
                    Password = Password
                )
            ).collect { state ->
                when(state){
                    is  ApiState.Loading ->{
//                            mBinding.progressBar.visibility=View.VISIBLE
                    }

                    is ApiState.Success ->{
//                           mBinding.progressBar.visibility=View.GONE
                        when (state.data.Status) {
                            "true" -> {
                                Bundle().let {
                                }
                                Log.d("resend", "${state.data.Status.toString()}")

                            }
                            "false" -> {


                            }
                            else -> {

                            }
                        }
                    }
                    is ApiState.Failure ->{
//                           mBinding.progressBar.visibility=View.GONE

                    }

                    is ApiState.Error ->{

                    }
                }
            }

        }

    }

    private fun apiCallVerifyOtp() {
        val hardcodedOtp = "1234" // Ensure this is only for debugging and not used in production
        val enteredOtp = "${mBinding.inputCode1.text}${mBinding.inputCode2.text}${mBinding.inputCode3.text}${mBinding.inputCode4.text}"

        // Check if UserId and MobileNo are not null

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModal.fetchingVerifyOtpApi2(
                    VerifyOtpRequest(
                        UserName = "AgriDept",
                        Password = "AD@432!",
                        MobileNo = "9535792314",
                        UserId ="RSK010101",
//                        OTP = enteredOtp // Include OTP in the request
                    )
                ).collect { state: ApiState<VerifyOtpResponse> ->
                    when (state) {
                        is ApiState.Loading -> {
                            mBinding.mainProgressBar.visibility = View.VISIBLE
                        }
                        is ApiState.Success -> {
                            mBinding.mainProgressBar.visibility = View.GONE
                            when (state.data.Status) {
                                "true" -> {
                                     otpget=state.data.OTP

                                }
                                "false" -> {
                                    requireContext().showToast("OTP Not Found")
                                }

                            }
                        }
                        is ApiState.Failure -> {
                            mBinding.wrongOtpText.visibility = View.VISIBLE
                            mBinding.mainProgressBar.visibility = View.GONE
                            mBinding.TvOTP.visibility = View.VISIBLE
                            Log.d("enterNumber", state.msg)
                        }
                        is ApiState.Error -> {
                            mBinding.mainProgressBar.visibility = View.GONE
                            requireContext().showToast("An error occurred")
                        }
                    }
                }
            }
        }
    }




    private fun handleBlankOtp() {
        Toast.makeText(context, "Please enter a valid OTP", Toast.LENGTH_SHORT).show()
    }

    private fun verifyEnteredOtp(): Boolean {
        return mBinding.inputCode1.text.isNotEmpty() &&
                mBinding.inputCode2.text.isNotEmpty() &&
                mBinding.inputCode3.text.isNotEmpty() &&
                mBinding.inputCode4.text.isNotEmpty()
    }

    private val watcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // YOUR CODE
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // YOUR CODE
        }

        override fun afterTextChanged(s: Editable) {
            if (verifyEnteredOtp()) {
                mBinding.btnContinue.background.setTint(Color.parseColor("#D74F58"))
                mBinding.btnContinue.strokeWidth = 0
                mBinding.btnContinue.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                mBinding.btnContinue.background.setTint(Color.parseColor("#F5F5F5"))
                mBinding.btnContinue.strokeWidth = 0
                mBinding.btnContinue.setTextColor(Color.parseColor("#B3B3B3"))
            }
        }
    }
}












//    companion object {
//        fun newInstance() = FragmentEnterOTP()
//    }
//
//    private var _binding: FragmentOtpEnterBinding? = null
//    private val binding get() = _binding!!
//
//    private val viewModel: FragmentEnterOTViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentOtpEnterBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        binding.btnContinue.setOnClickListener {
//            findNavController().navigate(R.id.action_fragment_otp_enter_to_fragment_home)
//        }
//
//        binding.backButton.setOnClickListener {
//            findNavController().navigate(R.id.otpnter_otp_login)
//        }
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

