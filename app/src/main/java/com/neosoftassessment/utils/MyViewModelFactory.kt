package com.neosoftassessment.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neosoftassessment.data.MainRepository
import com.neosoftassessment.ui.MainViewModel

class MyViewModelFactory constructor(private val repository: MainRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}