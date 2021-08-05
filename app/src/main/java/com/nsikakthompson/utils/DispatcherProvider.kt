package com.nsikakthompson.utils

import kotlinx.coroutines.Dispatchers

class DispatcherProvider {
    fun getIO() = Dispatchers.IO
    fun getMain() = Dispatchers.Main
}