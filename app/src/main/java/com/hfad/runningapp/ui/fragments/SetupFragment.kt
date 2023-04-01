package com.hfad.runningapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hfad.runningapp.R
import com.hfad.runningapp.base.BaseFragment
import com.hfad.runningapp.databinding.FragmentSetupBinding
import com.hfad.runningapp.utils.Constants.KEY_FIRST_TIME_TOGGLE
import com.hfad.runningapp.utils.Constants.KEY_NAME
import com.hfad.runningapp.utils.Constants.KEY_WEIGHT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : BaseFragment<FragmentSetupBinding>
    (FragmentSetupBinding::inflate) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    override fun prepareUI(savedInstanceState: Bundle?) {
        if(!isFirstAppOpen){
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.setupFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState,
                navOptions
            )
        }
    }

    override fun setListeners() {
        binding.tvContinue.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if (success) {
                findNavController().navigate(R.id.action_setupFragment_to_runFragment)
            } else {
                Snackbar.make(requireView(), "Please, enter all the fields", Snackbar.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun writePersonalDataToSharedPref(): Boolean {
        val name = binding.etName.text.toString()
        val weight = binding.etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()) {
            return false
        }
        sharedPref.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weight.toFloat())
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        val toolbarText = "Let's go, $name!"
        requireActivity().findViewById<TextView>(R.id.tvToolbarTitle).text = toolbarText
        return true
    }
}