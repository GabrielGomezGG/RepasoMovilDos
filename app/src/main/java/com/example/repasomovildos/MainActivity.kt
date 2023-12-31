package com.example.repasomovildos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.repasomovildos.ui.theme.MainViewModel
import com.example.repasomovildos.ui.theme.RepasoMovilDosTheme
import com.example.repasomovildos.ui.theme.ResponseUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepasoMovilDosTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen(mainViewModel)
                }
            }
        }
    }
}


@Composable
fun MainScreen(mainViewModel: MainViewModel) {
//    val posts by mainViewModel.posts.observeAsState(ResponseUiState.Loading)
    val posts by mainViewModel.posts.collectAsState(initial = ResponseUiState.Loading)

    when(posts){
        is ResponseUiState.Failure -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
//                Text(text = (posts as ResponseUiState.Failure).error.message.toString())
                Text(text = "No tenes internet")
            }
        }
        ResponseUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is ResponseUiState.Success -> {
            LazyColumn() {
                items((posts as ResponseUiState.Success).posts) {
                    Text(text = "Title: ${it.title}")
                    Spacer(modifier = Modifier.size(22.dp))
                }
            }
        }
    }
    

}