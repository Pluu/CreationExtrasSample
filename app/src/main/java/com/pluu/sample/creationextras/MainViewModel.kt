package com.pluu.sample.creationextras

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import logcat.logcat

class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val id: Int
) : ViewModel() {
    init {
        logcat {
            "savedStateHandle = (" + savedStateHandle.keys()
                .joinToString(separator = ", ") {
                    "${it}:${savedStateHandle.get<Any>(it)}"
                } + ")"
        }
        logcat { "id = $id" }
    }
}