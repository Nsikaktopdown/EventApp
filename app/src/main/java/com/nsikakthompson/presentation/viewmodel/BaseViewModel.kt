package com.nsikakthompson.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

open class BaseViewModel<T> : ViewModel() {
    fun uiState(): LiveData<T> = uiState
    protected val uiState: MutableLiveData<T> = MutableLiveData()

    /**
     * This is a scope for all coroutines launched by [BaseViewModel]
     * that will be dispatched in a Pool of Thread
     */
    protected val ioScope = CoroutineScope(Dispatchers.Default)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        ioScope.coroutineContext.cancel()
    }
}