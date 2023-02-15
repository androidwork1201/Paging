package com.example.pagingdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingdemo.model.Repo
import com.example.pagingdemo.model.Repository
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {

    /**調用Repository.getPagingData()並加上cachedIn()執行緩衝加載*/
    fun getPagingData(): Flow<PagingData<Repo>> {
        return Repository.getPagingData().cachedIn(viewModelScope)
    }
}