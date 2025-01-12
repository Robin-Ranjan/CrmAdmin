package com.scout.crmadmin.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scout.crmadmin.AddEmployeeForm
import com.scout.crmadmin.R
import com.scout.crmadmin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.cardAddEmpl.setOnClickListener {
            val intent = Intent(requireContext(), AddEmployeeForm::class.java)
            startActivity(intent)
        }
        return binding.root
//        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}