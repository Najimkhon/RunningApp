package com.hfad.runningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hfad.runningapp.R
import com.hfad.runningapp.base.BaseFragment
import com.hfad.runningapp.databinding.FragmentSettingsBinding
import com.hfad.runningapp.databinding.FragmentSetupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupFragment : BaseFragment<FragmentSetupBinding>
    (FragmentSetupBinding::inflate) {

    override fun prepareUI() {
        binding.tvContinue.setOnClickListener{
            findNavController().navigate(R.id.action_setupFragment_to_runFragment)
        }
    }

}