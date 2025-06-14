package com.WalkMateApp.walkmate.WalkMateApp.MainViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class WalkMateViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalkMateViewModel::class.java)) {
            return WalkMateViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}