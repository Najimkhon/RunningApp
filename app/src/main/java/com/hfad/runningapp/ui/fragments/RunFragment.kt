package com.hfad.runningapp.ui.fragments

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.runningapp.R
import com.hfad.runningapp.adapters.RunAdapter
import com.hfad.runningapp.base.BaseFragment
import com.hfad.runningapp.databinding.FragmentRunBinding
import com.hfad.runningapp.ui.viewmodels.MainViewModel
import com.hfad.runningapp.utils.Constants.REQUEST_CODE_LOCATION_PERMISSION
import com.hfad.runningapp.utils.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RunFragment : BaseFragment<FragmentRunBinding>(FragmentRunBinding::inflate),
    EasyPermissions.PermissionCallbacks {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var runAdapter: RunAdapter

    override fun prepareUI(savedInstanceState: Bundle?) {
        requestPermissions()
        binding.rvRuns.apply {
            runAdapter = RunAdapter(requireContext())
            adapter = runAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setListeners() {
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
        }
    }

    override fun setObservers() {
        viewModel.runsSortedByDate.observe(viewLifecycleOwner){
            runAdapter.submitList(it)
        }
    }

    private fun requestPermissions() {
        if (TrackingUtility.hasLocationPermissions(requireContext())) {
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}