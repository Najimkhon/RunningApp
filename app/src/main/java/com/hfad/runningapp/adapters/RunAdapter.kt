package com.hfad.runningapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hfad.runningapp.db.models.Run
import com.hfad.runningapp.ui.itemLayouts.RunItemLayout

class RunAdapter(val context: Context) : RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    val diffCallback = object : DiffUtil.ItemCallback<Run>(){
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(RunItemLayout(context))
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
        holder.layout.fillContent(run)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout = itemView as RunItemLayout
    }
}