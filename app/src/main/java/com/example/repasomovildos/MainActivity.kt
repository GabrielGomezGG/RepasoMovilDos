package com.example.repasomovildos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.repasomovildos.ui.theme.MainViewModel
import com.example.repasomovildos.ui.theme.RepasoMovilDosTheme

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
    val posts by mainViewModel.posts.observeAsState(emptyList())
    
    LazyColumn(){
        items(posts){
            Text(text = "Title: ${it.title}")
            Spacer(modifier = Modifier.size(22.dp))
        }
    }
}