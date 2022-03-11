package com.pluu.sample.creationextras

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras

///////////////////////////////////////////////////////////////////////////
// Activity
///////////////////////////////////////////////////////////////////////////
class MainActivity : AppCompatActivity() {

    // CreationExtras Key
    private val testExtraKey = object : CreationExtras.Key<Int> {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sample, Default Argument
        val extra = (intent.extras ?: Bundle()).also {
            it.putString("extra_key", "TEST")
        }
        intent.putExtras(extra)

        // Create, ViewModelProvider.Factory
        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Convert, SavedStateHandle
                val savedStateHandle = extras.createSavedStateHandle()
                val id = extras[testExtraKey] ?: -1
                return MainViewModel(
                    savedStateHandle = savedStateHandle,
                    id = id
                ) as T
            }
        }

        // Create, ViewModel
        val viewModel: MainViewModel by viewModels(
            extrasProducer = {
                MutableCreationExtras(defaultViewModelCreationExtras).apply {
                    set(testExtraKey, 1234)
                    set(DEFAULT_ARGS_KEY, bundleOf("self_extra_key" to "test"))
                }
            },
            factoryProducer = { factory }
        )
        viewModel.toString()
    }
}

