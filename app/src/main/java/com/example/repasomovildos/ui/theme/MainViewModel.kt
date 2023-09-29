package com.example.repasomovildos.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repasomovildos.data.PostRepository
import com.example.repasomovildos.data.api.ApiService
import com.example.repasomovildos.data.api.PostResponse
import com.example.repasomovildos.data.database.PostEntity
import com.example.repasomovildos.data.model.Post
import com.example.repasomovildos.domain.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface ResponseUiState {
    object Loading : ResponseUiState
    data class Success(val posts: List<PostEntity>) : ResponseUiState
    data class Failure(val error: Throwable) : ResponseUiState
}

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val postRepository: PostRepository,
    private val getPostUseCase: GetPostUseCase
) : ViewModel() {

    private val _posts = MutableStateFlow<ResponseUiState>(ResponseUiState.Loading)
    val posts : Flow<ResponseUiState> = _posts

    init{
        getTodos()
    }

    private fun getTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            getPostUseCase()
                .catch { _posts.value = ResponseUiState.Failure(it)  }
                .collect{
                    _posts.value = (ResponseUiState.Success(it))
                }
        }
    }

}