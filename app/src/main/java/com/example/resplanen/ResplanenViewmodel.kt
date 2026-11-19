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
data class Resplanenuistate(
    val Objektresa: Resplanen,
    val mutreslist: List<Resplanen>
)
class ResplanenViewmodel : ViewModel() {
    private var _uistate by mutableStateOf(Resplanenuistate(Resplanen(id=0),reslist))
        private set
    private var count=0
    fun addresa(){
        val nyresa=Resplanen(_uistate.Objektresa.Aktivitet,
            _uistate.Objektresa.plats,
            count,
            _uistate.Objektresa.gjort)
        count+=1
        _uistate = _uistate.copy(
            Objektresa = Resplanen(Aktivitet="",plats= "",id=0,),
            mutreslist = _uistate.mutreslist + nyresa
        )
    }
    fun onnewresa(aktivitet: String,plats: String,gjort: Boolean){
        _uistate=_uistate.copy(Objektresa=Resplanen(aktivitet,plats,count,gjort))
    }
}