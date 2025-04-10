package com.devspacecinenow.detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspacecinenow.common.data.remote.RetrofitClient
import com.devspacecinenow.common.data.remote.model.MovieDto
import com.devspacecinenow.detail.data.DetailService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val detailService: DetailService
) : ViewModel() {

    private val _uiMovieDetail = MutableStateFlow<MovieDto?>(null)
    val uiMovieDetail: StateFlow<MovieDto?> = _uiMovieDetail

    fun fetchMovieDetail(movieId: String) {
        if (_uiMovieDetail.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                val response = detailService.getMovieById(movieId)
                if (response.isSuccessful) {
                    _uiMovieDetail.value = response.body()
                } else {
                    Log.d(
                        "MovieDetailViewModel",
                        "Request Error :: ${response.errorBody()}"
                    )
                }
            }
        }
    }

    fun cleanMovieId() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(1000)
            _uiMovieDetail.value = null
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val detailService =
                    RetrofitClient.retrofitInstance.create(DetailService::class.java)
                return MovieDetailViewModel(
                    detailService
                ) as T
            }
        }
    }
}