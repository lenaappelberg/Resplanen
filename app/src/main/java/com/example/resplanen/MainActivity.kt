package com.example.resplanen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.resplanen.ui.theme.ResplanenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResplanenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Listofcards(modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun Listofcards(modifier: Modifier){
    LazyColumn() {
        items()
    }
}
@Composable
fun Travelcards(){
    Card() { }
}