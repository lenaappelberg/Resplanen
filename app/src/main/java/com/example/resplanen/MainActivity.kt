package com.example.resplanen

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.VisibilityThreshold
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
fun Title(text:String){
    Text(text)
}
@Composable
fun Listofcards(viewmodel: ResplanenViewmodel=viewModel() ){
    val resor=viewmodel.uistate.mutreslist
    LazyColumn() {
        items(resor,key={it.id}){res->
           Travelcards(res.Aktivitet,res.plats,res.id)
        }
    }
}
@Composable
fun Travelcards(aktivitet: String,plats:String,id:Int){
    Card() {
        Text(text = aktivitet)
        Addaktivitet(id)
        Text(text = plats)
        Markasread(id=id)
        Delete(id=id)
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
fun editbutton(viewmodel: ResplanenViewmodel=viewModel()){
    val plats=viewmodel.uistate.plats
    Button(onClick ={ } ) { }
}
@Composable
fun showtextfield(viewmodel: ResplanenViewmodel=viewModel(), visibility: Visibility){
    val plats=viewmodel.uistate.plats
    TextField(modifier = Modifier, value = plats, onValueChange = {viewmodel.onnewplatsresa(it)})
}
@Composable
fun Addaktivitet(id: Int,viewmodel: ResplanenViewmodel=viewModel()){
    val aktivitet=viewmodel.uistate.aktivitet
    Text(text = "Edit an activity")
    TextField(value = aktivitet, onValueChange = {viewmodel.onnewaktivitetresa(it)})
    Button(onClick = {viewmodel.editaktivitetresa(id,viewmodel.uistate.aktivitet)}) {Text(text = "edit activity") }
}