package com.nsikakthompson.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nsikakthompson.R
import com.nsikakthompson.databinding.EventItemBinding
import com.nsikakthompson.databinding.FragmentEventsBinding
import com.nsikakthompson.presentation.viewmodel.EventState
import com.nsikakthompson.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class EventFragment : Fragment() {
    private val viewModel by viewModel<EventViewModel>()
    private val eventAdapter = EventAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        with(binding.rvEvents){
            layoutManager = LinearLayoutManager(this.context)
            adapter = eventAdapter
        }


        viewModel.getEvents()
        subscribeUi(binding, eventAdapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentEventsBinding, adapter: EventAdapter) {
        viewModel.uiState().observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is EventState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorGroup.visibility = View.GONE

                }
                is EventState.data -> {
                    Timber.v("${state.events} events")
                        eventAdapter.submitList(state.events.value)
                }

                is EventState.Loading -> {
                    binding.errorGroup.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is EventState.Error -> {
                    binding.errorGroup.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.tvError.text =  state.message
                }
            }
        })
    }
}