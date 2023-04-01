package com.hfad.runningapp.ui.itemLayouts

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.hfad.runningapp.databinding.ItemRunBinding
import com.hfad.runningapp.db.models.Run
import com.hfad.runningapp.utils.TrackingUtility
import java.text.SimpleDateFormat
import java.util.*

class RunItemLayout(context: Context) : RelativeLayout(context) {

    private val binding = ItemRunBinding.inflate(LayoutInflater.from(context), this, true)

    fun fillContent(run: Run) {
        Glide.with(this).load(run.img).into(binding.ivRunImage)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = run.timestamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        binding.tvDate.text = dateFormat.format(calendar.time)

        binding.tvAvgSpeed.text = "${run.avgSpeedInKMH}km/h"
        binding.tvDistance.text = "${run.distanceInMeters / 1000f}km"
        binding.tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)
        binding.tvCalories.text = "${run.caloriesBurned}kcal"
    }
}