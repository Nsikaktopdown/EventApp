package com.nsikakthompson.presentation.event_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.nsikakthompson.R
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.databinding.FragmentEventDetailsBinding
import com.nsikakthompson.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class EventDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailsBinding
    private val viewModel by viewModel<EventViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailsBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /**
         * get arguments
         */
        var eventArgs = EventDetailsFragmentArgs.fromBundle(requireArguments())
        val eventEntity: EventEntity = eventArgs.event!!

        binding.toolbarLayout.title = eventEntity?.name


        /**
         * update binding variable
         */
        binding.apply {
            event = eventEntity

        }

        /**
         *  fetch event from db if any
         */
        viewModel.getEventById(eventEntity?.id!!)
        viewModel.event.observe(viewLifecycleOwner, Observer {
            binding.apply {
                event = it
            }

            Timber.e("event" + it.toString())
        })

        /**
         * add event to wish list
         */
        binding.makeWish.setOnClickListener { view ->
            viewModel.addWishList(eventEntity!!.copy(isWish = true))
            viewModel.getEventById(eventEntity?.id!!)
        }

        /**
         * remove event from wish list
         */
        binding.removeWish.setOnClickListener { view ->
            viewModel.removeWishList(eventEntity!!)
            viewModel.getEventById(eventEntity?.id!!)
        }
    }
}