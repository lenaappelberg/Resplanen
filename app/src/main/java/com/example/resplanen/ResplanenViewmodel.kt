package com.example.resplanen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
data class Resplanen  (
    val Aktivitet:String="",
    val plats:String="",
    val id:Int,
    val gjort: Boolean=false
)
val reslist=listOf<Resplanen>(

)
class ResplanenViewmodel : ViewModel() {
    private var _uistate by mutableStateOf(Resplanen(id=0))
        private set

}