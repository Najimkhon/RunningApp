package com.hfad.runningapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hfad.runningapp.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    val mainRepository: MainRepository
): ViewModel() {


}