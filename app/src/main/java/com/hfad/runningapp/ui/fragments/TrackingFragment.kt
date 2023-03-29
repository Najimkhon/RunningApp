package com.hfad.runningapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.hfad.runningapp.R
import com.hfad.runningapp.base.BaseFragment
import com.hfad.runningapp.databinding.FragmentTrackingBinding
import com.hfad.runningapp.services.TrackingService
import com.hfad.runningapp.ui.viewmodels.MainViewModel
import com.hfad.runningapp.utils.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.hfad.runningapp.utils.Constants.ACTION_START_OR_RESUME_SERVICE

class TrackingFragment : BaseFragment<FragmentTrackingBinding>(FragmentTrackingBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    private var map: GoogleMap? = null


    override fun prepareUI(savedInstanceState: Bundle?) {
        super.prepareUI(savedInstanceState)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync{
            map = it
        }
    }

    override fun setListeners() {
        binding.btnToggleRun.setOnClickListener{
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onResume()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    private fun sendCommandToService(action: String)=
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }



}