package com.example.resplanen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
data class Resplanen  (
    val Titel:String
)
class ResplanenViewmodel : ViewModel() {
    private var _uistate by mutableStateOf(Resplanen(""))
        private set


}