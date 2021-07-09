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
import com.nsikakthompson.data.Result
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

        with(binding.rvEvents) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = eventAdapter
        }



        subscribeUi(binding, eventAdapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentEventsBinding, adapter: EventAdapter) {
        viewModel.events.observe(viewLifecycleOwner, Observer { events ->
            binding.progressBar.visibility = View.GONE
            eventAdapter.submitList(events)

        })
    }
}