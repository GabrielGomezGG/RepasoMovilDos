package com.example.repasomovildos.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repasomovildos.data.PostRepository
import com.example.repasomovildos.data.api.ApiService
import com.example.repasomovildos.data.api.PostResponse
import com.example.repasomovildos.data.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface ResponseUiState {
    object Loading : ResponseUiState
    data class Success(val posts: List<Post>) : ResponseUiState
    data class Failure(val error: Throwable) : ResponseUiState
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _posts = MutableLiveData<ResponseUiState>(ResponseUiState.Loading)
    val posts : LiveData<ResponseUiState> = _posts

    init{
        getTodos()
    }

    private fun getTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            val todos = postRepository.getPost()

            _posts.postValue(ResponseUiState.Success(todos))
        }
    }

}