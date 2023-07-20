package com.example.recyclaearviewpagination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

import com.example.recyclaearviewpagination.paging3.USerDataPagingSource

class UserViewModel : ViewModel() {

    val flow = Pager(PagingConfig(20)){
        USerDataPagingSource()
    }.flow.cachedIn(viewModelScope)
}