package com.example.apiapptest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapptest.api.RetrofitClient
import com.example.apiapptest.model.RespuestaSeries
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SeriesViewModel : ViewModel() {

    private val _series = MutableStateFlow<RespuestaSeries?>(null)
    val series: StateFlow<RespuestaSeries?> get() = _series

    fun fetchSeries() {
        viewModelScope.launch {
            _series.value = RetrofitClient.apiService.getSeries()
        }
    }
}