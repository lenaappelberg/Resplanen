package com.example.resplanen

import androidx.lifecycle.ViewModel
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
    val aktivitet: String="",
    val plats:String="",
    val mutreslist: List<Resplanen> =reslist
)
class ResplanenViewmodel : ViewModel() {
     var uistate by mutableStateOf(Resplanenuistate())
        private set
    private var count=0
    fun addresa(){
        val nyresa=Resplanen(uistate.aktivitet,
            uistate.plats,
            count,)
        count+=1
        uistate = uistate.copy(
            aktivitet="",
            plats= "",
            mutreslist = uistate.mutreslist + nyresa
        )
    }
    //,plats: String,gjort: Boolean
    fun onnewaktivitetresa(aktivitet: String){
        uistate=uistate.copy(aktivitet=aktivitet)
    }
    fun onnewplatsresa(plats: String){
        uistate=uistate.copy(plats=plats)
    }
}