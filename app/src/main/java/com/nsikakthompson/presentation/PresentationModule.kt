package com.nsikakthompson.presentation

import com.nsikakthompson.domain.EventRepository
import com.nsikakthompson.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var presentationModule  = module {
   viewModel {
        EventViewModel(get(), get(), get(), get(), get(), get())
    }
}