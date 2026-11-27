package com.example.resplanen

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Scaffold(modifier = Modifier.fillMaxSize(),snackbarHost = {
                    SnackbarHost(snackbarHostState)
                },
                    topBar = { TopAppBar({ Title("hello") }) },floatingActionButton = { ExtendedFloatingActionButton(onClick = {}, content = {
                        Text(text = "undo")
                    }) })
                { innerPadding ->

                    TopAppBar({ Title("hello") })


                    Column(modifier = Modifier.padding(innerPadding)) {
                        Statisticrow()
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
fun Title(text:String,viewmodel: ResplanenViewmodel=viewModel()){
    val state=viewmodel.uistate

    Row() {Text(text)
        //Ångra knapp
        //priorities filter
        Row() {
            priorities.forEach { filter ->
                FilterChip(
                    selected = state.priority == filter,
                    onClick = { viewmodel.onFilterChange(filter) },
                    label = { Text(filter) },
                )
            }
        }
        //Searchbar
        FilterChip(selected =state.sortByTitle,
            onClick = {viewmodel.toggleSort()},
            label = {Text("Low-high")})
                }
    //undoremove button

}
@Composable
fun Listofcards(viewmodel: ResplanenViewmodel=viewModel() ){
    val resor=viewmodel.uistate.mutreslist
    Row() {
        Column() {
            Text(text = "Resta destinationer")
            LazyColumn() {

            }
        }
        Column() {
            Text(text = "Oresta Destinationer")
            LazyColumn() {
                items(resor,key={it.id}){res->
                    Travelcards(Resplanen(res.Aktivitet,res.priority,res.id))
                }
            }
        }

    }

}
@Composable
fun Travelcards(Resplanen: Resplanen){
    Card() {
        Text(text = Resplanen.Aktivitet)
        Addaktivitet(Resplanen.id)
        Markasread(id= Resplanen.id)
        Text(text = Resplanen.priority)
        Delete(id= Resplanen.id)
    }
}
@Composable
fun Inputelement(viewModel: ResplanenViewmodel = viewModel() ){
    //global state management applies everywhere which means that edits also appear here
    val item=viewModel.uistate.aktivitet
    //var checked by remember { mutableStateOf(true,false,false) }
    var checked=viewModel.uistate.priority
    Column() {
        Text(text = "Input an activitet")
        TextField(value = item, onValueChange = {viewModel.onnewaktivitetresa(it)})
        Text(text = "Priority")
        Row() {
            priorities.forEach {
                priority->
                FilterChip(
                    selected = checked==priority,
                    onClick = {viewModel.onnewpriority(priority)},
                    label = {Text(priority)}
                )
            }
        }

        Button(onClick = {viewModel.addresa()}, enabled = if (item.isNotEmpty())true else false) { Text(text = "Add destination") }
    }
}
@Composable
fun Markasread(viewmodel: ResplanenViewmodel=viewModel(),id:Int){
    Button(onClick = {viewmodel.updateraresa(id)}) {Text(text = if (viewmodel.uistate.mutreslist[id].gjort==true) "Unmark Holiday" else "Mark Holiday as done") }
}
@Composable
fun Delete(viewmodel: ResplanenViewmodel=viewModel(),id:Int){
    Button(onClick = {viewmodel.tabortresa(id)}) {Text(text = "Delete Holiday") }
}
@Composable
fun Statisticrow(viewmodel: ResplanenViewmodel=viewModel()){
    Text(text ="${viewmodel.uistate.mutreslist.filter { it.gjort==true }.size} / ${viewmodel.uistate.mutreslist.size} done")
}
@Composable
fun Addaktivitet(id: Int,viewmodel: ResplanenViewmodel=viewModel()){
    val newaktivitet=viewmodel.uistate.newaktivitet
    Text(text = "Edit an activity")
    TextField(value = newaktivitet, onValueChange = {viewmodel.starteditresa(it)})
    Button(onClick = {viewmodel.editaktivitetresa(id,newaktivitet)}) {Text(text = "edit activity") }
}
@Composable
fun searchbar(){
    TextField(value="", onValueChange = {})
    Button(onClick = {}) { }
}
@Composable fun undoremovebutton(){
Button(onClick = {}) {Text(text = "undoremove") }
}