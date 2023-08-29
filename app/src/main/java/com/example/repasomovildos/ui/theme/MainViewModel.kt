package com.example.repasomovildos.ui.theme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repasomovildos.data.Post
import com.example.repasomovildos.data.RetrofitAlgo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts : LiveData<List<Post>> = _posts

    init{
        getTodos()
    }

    private fun getTodos(){
        viewModelScope.launch {
            val todos = RetrofitAlgo.getApi.getPosts().body()!!

            _posts.value = todos

            _posts.value!!.forEach {
                Log.i("titi", it.body)
            }

        }
    }

}