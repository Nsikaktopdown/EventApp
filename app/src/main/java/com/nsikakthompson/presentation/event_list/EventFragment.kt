package com.nsikakthompson.presentation.event_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsikakthompson.databinding.FragmentEventsBinding
import com.nsikakthompson.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        with(binding.rvEvents) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = eventAdapter
        }

//        observeNetworkState(binding)
//        subscribeUi(binding)

        binding.countLayout.tvCount.setOnClickListener {
            findNavController(this).navigate(EventFragmentDirections.actionEventFragmentToWishListFragment())
        }

        return binding.root
    }

    override fun onStart() {
        viewModel.getWishCount()
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

//    private fun subscribeUi(binding: FragmentEventsBinding) {
//        viewModel.events.observe(viewLifecycleOwner, Observer { events ->
//            if (events.isNotEmpty()) {
//                eventAdapter.submitList(events)
//            }
//
//        })
//
//        viewModel.wishCount.observe(viewLifecycleOwner, Observer {
//            binding.countLayout.tvCount.visibility = if (it == 0) View.GONE else View.VISIBLE
//            binding.countLayout.tvCount.text = it.toString()
//
//        })
//
//    }


//    private fun observeNetworkState(binding: FragmentEventsBinding) {
//        viewModel.networkState?.observe(viewLifecycleOwner, Observer {
//            when (it.state) {
//                EventState.RUNNING -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                }
//                EventState.FAILED -> {
//                    binding.progressBar.visibility = View.GONE
//                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
//                }
//                EventState.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
//                }
//            }
//        })
//    }
}