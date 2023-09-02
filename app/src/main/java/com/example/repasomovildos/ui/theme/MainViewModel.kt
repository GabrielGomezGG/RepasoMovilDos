package com.example.repasomovildos.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repasomovildos.data.api.ApiService
import com.example.repasomovildos.data.api.PostResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _posts = MutableLiveData<List<PostResponse>>()
    val posts : LiveData<List<PostResponse>> = _posts

    init{
        getTodos()
    }

    private fun getTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            val todos = apiService.getPosts().body()!!

            _posts.postValue(todos)
        }
    }

}