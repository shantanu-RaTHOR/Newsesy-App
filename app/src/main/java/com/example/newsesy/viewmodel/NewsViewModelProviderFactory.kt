package com.example.newsesy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsViewModelProviderFactory(context: Context) :ViewModelProvider.Factory
{
    var context=context
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return com.example.newsesy.viewmodel.ViewModel(context) as T
    }
}