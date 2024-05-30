package com.example.parivikshaka.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parivikshaka.R
import com.example.parivikshaka.adapters.CardItemAdapter
import com.example.parivikshaka.databinding.FragmentCardSampleBinding
import com.example.parivikshaka.db.ApiState
import com.example.parivikshaka.models.TargetList
import com.example.parivikshaka.models.TargetListRequest
import com.example.parivikshaka.models.TargetRequest
import com.example.parivikshaka.models.TargetResponse
import com.example.parivikshaka.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardSampleFragment : Fragment() {

    private lateinit var binding: FragmentCardSampleBinding
    private lateinit var itemsAdapter: CardItemAdapter
    private val mViewModal: TargetViewmodel by viewModels()

    private lateinit var radioButton: RadioButton
    private var DistrictId: Int? = null
    private var FinancialYearID: Int? = null
    private var HobliId: Int? = null
    private var RoleID: Int? = null
    private var TalukId: Int? = null
    private var TypeId: Int? = 1
    private val viewmodal: OtpLoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardSampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupRadioGroupListener()
        fetchTragetListData()
        fetchTargetData()
    }




    private fun setupRecyclerView() {
        itemsAdapter = CardItemAdapter()
        binding.viewTicketRecyclerView.apply {
            adapter = itemsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupRadioGroupListener() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioSeed -> {
                    TypeId = 1
                }
                R.id.radioPesticide -> {
                    TypeId = 2
                }
                R.id.radioFertilizer -> {
                    TypeId = 3
                }
            }
            fetchTragetListData()
        }
    }

    private fun fetchTargetData() {
        Log.d("TargetRequest", "DistrictId: ${viewmodal.getlogin().District}, FinancialYearID: 23 or 24, HobliId: ${viewmodal.getlogin().Hobli}, RoleID: ${viewmodal.getlogin().RoleId}, TalukId: ${viewmodal.getlogin().Taluk}")

        // Hardcoded Financial Year IDs
        val financialYears = listOf(23, 24)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                    mViewModal.fetchingTargetApi(
                        TargetRequest(
                            UserName = "AgriDept",
                            Password = "AD@432!",
                            DistrictId = viewmodal.getlogin().District,
                            FinancialYearID = 23,
                            HobliId = viewmodal.getlogin().Hobli,
                            RoleID = viewmodal.getlogin().RoleId,
                            TalukId = viewmodal.getlogin().Taluk
                        )
                    ).collect { state: ApiState<TargetResponse> ->
                        when (state) {
                            is ApiState.Loading -> {
                                // Handle loading state if needed
                            }
                            is ApiState.Success -> {
                                when (state.data.Status) {
                                    "SUCCESS" -> {
                                        handleSuccessResponse(state.data)
                                    }
                                    "FAILURE" -> {
                                        requireContext().showToast("Target not found for Financial Year ")
                                    }
                                    else -> {
                                        requireContext().showToast(state.data.Message ?: "Target Data not found for Financial Year ")
                                    }
                                }
                            }
                            is ApiState.Failure -> {
                                Log.d("fetchTargetData", state.msg)
                            }
                            is ApiState.Error -> {
                                requireContext().showToast("An error occurred while fetching data for Financial Year ")
                            }
                        }
                    }

            }
        }
    }

    private fun handleSuccessResponse(data: TargetResponse) {
        val pesticideTargetCount = data.SPTLTargetList.firstOrNull()?.PesticideTarget ?: 0
        val seedTargetCount = data.SPTLTargetList.firstOrNull()?.SeedTarget ?: 0
        val fertilizerTargetCount = data.SPTLTargetList.firstOrNull()?.FertilizerTarger ?: 0

        val targetStringBuilder = StringBuilder("Target:\n")
        if (pesticideTargetCount > 0) {
            targetStringBuilder.append("Pesticide Target: $pesticideTargetCount\n")
        }
        if (seedTargetCount > 0) {
            targetStringBuilder.append("Seed Target: $seedTargetCount\n")
        }
        if (fertilizerTargetCount > 0) {
            targetStringBuilder.append("Fertilizer Target: $fertilizerTargetCount\n")
        }

        binding.targetTextView.text = targetStringBuilder.toString()
    }

   private fun fetchTragetListData() {
        Log.d("TargetListRequest", "DistrictId: $DistrictId, TypeId: $TypeId")
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModal.fetchingTargetListApi(
                    TargetListRequest(
                        UserName = "AgriDept",
                        Password = "AD@432!",
                        DistrictId = viewmodal.getlogin().District,
                        TypeId = TypeId
                    )
                ).collect { state: ApiState<TargetList> ->
                    when (state) {
                        is ApiState.Loading -> {
                            // Handle loading state if needed
                        }
                        is ApiState.Success -> {
                            when (state.data.Status) {
                                "SUCCESS" -> {
                                    handleTargetResponse(state.data)
                                }
                                "FAILURE" -> {
                                    requireContext().showToast("Target not found")
                                }
                                else -> {
                                    requireContext().showToast(state.data.Message ?: "Target not found")
                                }
                            }
                        }
                        is ApiState.Failure -> {
                            Log.d("fetchTragetListData", state.msg)
                        }
                        is ApiState.Error -> {
                            requireContext().showToast("An error occurred")
                        }
                    }
                }
            }
        }
    }

    private fun handleTargetResponse(data: TargetList) {
       val applicationNames = data.SPTLDetailsList.map { it.ApplicationName ?: "" }
        itemsAdapter.updateItems(applicationNames)
    }



}
