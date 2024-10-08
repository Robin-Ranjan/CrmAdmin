package com.scout.crmadmin

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.scout.crmadmin.bottomSheet.BloodGroupBottomSheet
import com.scout.crmadmin.bottomSheet.DesignationBottomSheet
import com.scout.crmadmin.databinding.ActivityAddEmployeeFormBinding
import com.scout.crmadmin.utill.Utill
import com.scout.crmadmin.viewModals.SharedViewModal
import java.util.Calendar

class AddEmployeeForm : AppCompatActivity() {
    private val binding: ActivityAddEmployeeFormBinding by lazy {
        ActivityAddEmployeeFormBinding.inflate(layoutInflater)
    }
    private val sharedViewModal : SharedViewModal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.show()
        supportActionBar?.title = "Add Employee Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Utill.statusBarColor(R.color.status_bar_color,this)
        setGenderSpinner()
        setBloodGroup()
        setDesignation()
        binding.empDob.setOnClickListener{
            setDatePicker()
        }

    }

    private fun setDatePicker(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                binding.empDob.text = SpannableStringBuilder(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()
    }
    private fun setDesignation(){
        binding.empDesignation.setOnClickListener{
            DesignationBottomSheet().show(supportFragmentManager,"DesignationBottomSheet")
        }
        sharedViewModal.selectDesignation.observe(this, Observer { designation ->
            binding.empDesignation.text = designation
        })
    }

    private fun setBloodGroup(){
        binding.empBloodGrp.setOnClickListener{
            BloodGroupBottomSheet().show(supportFragmentManager,"BloodGroupBottomSheet")
        }
        sharedViewModal.selectedBloodGroup.observe(this, Observer { bloodGrp ->
            binding.empBloodGrp.text = bloodGrp
        })
    }

    private fun setGenderSpinner(){
        val adapter = object : ArrayAdapter<String>(this, R.layout.custom_spinner, resources.getStringArray(R.array.gender)) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view: View
                if (convertView == null) {
                    // Inflate a new view if convertView is null
                    view = LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false)
                } else {
                    // Reuse the recycled view
                    view = convertView
                }
                val textView = view.findViewById<TextView>(R.id.spinner_text)
                textView.text = getItem(position)
                return view
            }
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view: View
                if (convertView == null) {
                    // Inflate a new dropdown view if convertView is null
                    view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
                } else {
                    // Reuse the recycled dropdown view
                    view = convertView
                }
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.text = getItem(position)
                return view
            }
        }
        binding.empGender.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
