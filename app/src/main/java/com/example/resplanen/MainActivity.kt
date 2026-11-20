package com.example.resplanen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.resplanen.ui.theme.ResplanenTheme
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResplanenTheme {

                Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopAppBar({ Title("hello") }) })
                { innerPadding ->
                    TopAppBar({ Title("hello") })
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Listofcards(

                        )
                        Inputelement()
                    }
                }
            }
        }
    }
}

@Composable
fun Title(text:String){
    Text(text)
}
@Composable
fun Listofcards(viewmodel: ResplanenViewmodel=viewModel() ){
    val resor=viewmodel.uistate.mutreslist
    LazyColumn() {
        items(resor,key={it.id}){res->
           Travelcards(res.Aktivitet,res.plats)
        }
    }
}
@Composable
fun Travelcards(aktivitet: String,plats:String){
    Card() {
        Text(text = aktivitet)
        Text(text = plats)
    }
}
@Composable
fun Inputelement(viewModel: ResplanenViewmodel = viewModel() ){
    val item=viewModel.uistate.aktivitet
    val plats=viewModel.uistate.plats
    Column() {
        Text(text = "Input an activitet")
        TextField(value = item, onValueChange = {viewModel.onnewaktivitetresa(it)})
        Text(text = "Input a destination")
        TextField(value = plats, onValueChange = {viewModel.onnewplatsresa(it)})
        Button(onClick = {viewModel.addresa()}) { Text(text = "Add destination") }
    }
}