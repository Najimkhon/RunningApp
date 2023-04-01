package com.hfad.runningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.hfad.runningapp.base.BaseFragment
import com.hfad.runningapp.databinding.FragmentStatisticsBinding
import com.hfad.runningapp.ui.viewmodels.StatisticsViewModel
import com.hfad.runningapp.utils.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.round

@AndroidEntryPoint
class StatisticsFragment :
    BaseFragment<FragmentStatisticsBinding>(FragmentStatisticsBinding::inflate) {

    private val viewModel: StatisticsViewModel by viewModels()

    override fun setObservers() {
        viewModel.totalTimeRun.observe(viewLifecycleOwner) {
            it?.let {
                val totalTimeRun = TrackingUtility.getFormattedStopWatchTime(it)
                binding.tvTotalTime.text = totalTimeRun
            }
        }
        viewModel.totalDistance.observe(viewLifecycleOwner) {
            it?.let {
                val km = it / 1000f
                val totalDistance = round(km * 10f) / 10f
                val totalDistanceString = "${totalDistance}km"
                binding.tvTotalDistance.text = totalDistanceString
            }
        }

        viewModel.totalAvgSpeed.observe(viewLifecycleOwner){
            it?.let {
                val avgSpeed = round(it * 10f) / 10f
                val avgSpeedString =  "${avgSpeed}km/h"
                binding.tvAverageSpeed.text = avgSpeedString
            }
        }

        viewModel.totalCaloriesBurned.observe(viewLifecycleOwner){
            it?.let {
                val calories = "${it}kcal"
                binding.tvTotalCalories.text = calories
            }
        }
    }

}