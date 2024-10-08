package com.scout.crmadmin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView

class BloodGroupAdapter(
    private val bloodGroups: List<String>,
    private val onItemClicked: (String) -> Unit
) :RecyclerView.Adapter<BloodGroupAdapter.BloodGroupViewHolder>() {
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_for_bottomsheet, parent, false)
        return BloodGroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: BloodGroupViewHolder, position: Int) {
        val bloodGroup = bloodGroups[holder.adapterPosition]
        holder.radioButton.text = bloodGroup
        holder.radioButton.isChecked = holder.adapterPosition == selectedPosition

        holder.radioButton.setOnClickListener {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
            onItemClicked(bloodGroup)
        }
    }

    override fun getItemCount() = bloodGroups.size

    class BloodGroupViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        val radioButton: RadioButton = view.findViewById(R.id.radioButton)
    }
}