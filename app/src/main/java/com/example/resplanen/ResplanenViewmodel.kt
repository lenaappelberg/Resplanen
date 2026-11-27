package com.example.resplanen

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
data class Resplanen  (
    val Aktivitet:String="",
    val priority:String="",
    val id:Int,
    val gjort: Boolean=false
)
val reslist=listOf<Resplanen>(

)
data class Resplanenuistate(
    val aktivitet: String="",
    val newaktivitet:String="",
    val priority:String=priorities[0],
    val sortByTitle: Boolean = false,
    val filter:String="ALLA",
    val mutreslist: List<Resplanen> =reslist
)
val priorities=listOf<String>("Low","Medium","High")
val filters=listOf<String>("Low","Medium","High")
class ResplanenViewmodel : ViewModel() {
    var uistate by mutableStateOf(Resplanenuistate())
        private set
    private var count = 0
    private var lastRemoved: Resplanen? = null
    private var lastRemovedIndex = 0
    fun addresa() {
        val aktivitet = uistate.aktivitet.trim()
        if (aktivitet.isEmpty()) return
        val nyresa = Resplanen(
            uistate.aktivitet,uistate.priority,
            count,
        )
        count += 1
        uistate = uistate.copy(
            aktivitet = "",
            mutreslist = uistate.mutreslist + nyresa
        )
    }
    //,plats: String,gjort: Boolean
    fun onnewaktivitetresa(aktivitet: String) {
        uistate = uistate.copy(aktivitet = aktivitet)
    }
    fun onnewpriority(priority: String){
        uistate=uistate.copy(priority = priority)
    }
    fun updateraresa(id: Int) {
        uistate = uistate.copy(
            mutreslist = uistate.mutreslist.map { if (it.id == id) it.copy(gjort = !it.gjort) else it }
        )
    }
    fun tabortresa(id: Int) {
        val index = uistate.mutreslist.indexOfFirst { it.id == id }
        if (index == -1) return
        lastRemoved = uistate.mutreslist[index]
        lastRemovedIndex = index
        uistate = uistate.copy(mutreslist = uistate.mutreslist.filter { it.id != id })
    }
    fun starteditresa(newaktivitet: String){
        uistate=uistate.copy(
            newaktivitet = newaktivitet
        )
    }
    fun editaktivitetresa(id: Int, newaktivitet: String) {
        uistate = uistate.copy(
            mutreslist = uistate.mutreslist.map { if (it.id == id) it.copy(Aktivitet = newaktivitet) else it }
        )
    }
    fun onFilterChange(filter: String) {
        uistate = uistate.copy(filter = filter)
    }

    fun toggleSort() {
        uistate = uistate.copy(sortByTitle = !uistate.sortByTitle)
    }
    val filtered = uistate.mutreslist.filter {
        when (uistate.filter) {
            "Low" -> it.priority=="low"
            "Medium" -> it.priority=="medium"
            "High"-> it.priority =="High"
            else -> true
        }
    }
    val shown = if (uistate.sortByTitle) {
        filtered.sortedBy { it.Aktivitet.lowercase() }
    } else {
        filtered
    }
    fun undoRemove() {
        val res = lastRemoved ?: return
        val resor = uistate.mutreslist
        uistate = uistate.copy(
            mutreslist = resor.take(lastRemovedIndex) + res + resor.drop(lastRemovedIndex)
        )
        lastRemoved = null
    }

//    fun sokaresa(Aktivitet: String){
//        uistate=uistate.copy(
//            mutreslist = uistate.mutreslist.map { if (it.Aktivitet == Aktivitet) return it else it }
//        )
//    }
}
    //new list?
