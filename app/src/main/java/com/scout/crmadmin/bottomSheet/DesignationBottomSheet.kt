package com.scout.crmadmin.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.scout.crmadmin.BloodGroupAdapter
import com.scout.crmadmin.viewModals.SharedViewModal
import com.scout.crmadmin.databinding.BottomsheetBloodGrpBinding

class DesignationBottomSheet :BottomSheetDialogFragment() {
    private val designations = listOf("State Coordinator", "Human Resource Manager", "Human Resource Manager- IT", "Human Resource Executive",
        "Senior Android Developer", "Android Developer", "Digital Marketing(Intern)", "Graphic Designer(Intern)","District Organisation Commissioner",
        "School Coordinator","Assistant School Coordinator","Web Developer","Full Stack Developer","Digital Marketing Manager")
    private var _binding: BottomsheetBloodGrpBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModal: SharedViewModal by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetBloodGrpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewBloodGroups.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewBloodGroups.adapter =
            BloodGroupAdapter(designations) { selectedDesignation ->
                sharedViewModal.getDesignation(selectedDesignation)
                dismiss()
            }
    }
}