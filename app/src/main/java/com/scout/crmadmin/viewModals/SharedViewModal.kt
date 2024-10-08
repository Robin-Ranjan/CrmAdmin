package com.scout.crmadmin.viewModals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModal : ViewModel() {
    private val _selectedBloodGroup  = MutableLiveData<String>()
    val selectedBloodGroup :LiveData<String> get() = _selectedBloodGroup

    private val _selectDesignation = MutableLiveData<String>()
    val selectDesignation :LiveData<String> get() = _selectDesignation

    fun getBloodGroup(bloodGroup:String){
        _selectedBloodGroup.value = bloodGroup
    }

    fun getDesignation(designation:String){
        _selectDesignation.value = designation
    }
}