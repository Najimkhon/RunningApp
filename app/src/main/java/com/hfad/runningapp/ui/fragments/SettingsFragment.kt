package com.hfad.runningapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.hfad.runningapp.R
import com.hfad.runningapp.base.BaseFragment
import com.hfad.runningapp.databinding.FragmentSettingsBinding
import com.hfad.runningapp.utils.Constants.KEY_NAME
import com.hfad.runningapp.utils.Constants.KEY_WEIGHT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun prepareUI(savedInstanceState: Bundle?) {
        loadFieldsFromSharedPref()
    }

    override fun setListeners() {
        binding.btnApplyChanges.setOnClickListener{
            val success = applyChangesToSharedPref()
            if(success){
                Snackbar.make(requireView(), "Saved changes", Snackbar.LENGTH_LONG ).show()
            }else{
                Snackbar.make(requireView(), "Please, fill out all the fields", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun loadFieldsFromSharedPref(){
        val name = sharedPreferences.getString(KEY_NAME, "")
        val weight = sharedPreferences.getFloat(KEY_WEIGHT, 80f)
        binding.etName.setText(name)
        binding.etWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPref(): Boolean {
        val nameText = binding.etName.text.toString()
        val weightText = binding.etWeight.text.toString()
        if (nameText.isEmpty() || weightText.isEmpty()) {
            return false
        }
        sharedPreferences.edit()
            .putString(KEY_NAME, nameText)
            .putFloat(KEY_WEIGHT, weightText.toFloat())
            .apply()
        val toolbarText = "Let's go, $nameText!"
        requireActivity().findViewById<TextView>(R.id.tvToolbarTitle).text = toolbarText
        return true
    }

}