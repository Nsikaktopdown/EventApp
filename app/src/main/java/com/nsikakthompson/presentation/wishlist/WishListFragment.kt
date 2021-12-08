package com.nsikakthompson.presentation.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsikakthompson.databinding.FragmentEventsBinding
import com.nsikakthompson.databinding.FragmentWishlistBinding
import com.nsikakthompson.presentation.event_list.EventAdapter
import com.nsikakthompson.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishListFragment : Fragment() {
    lateinit var binding: FragmentWishlistBinding
    private val viewModel by viewModel<EventViewModel>()
    private val eventAdapter = EventAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishlistBinding.inflate(inflater)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }

        with(binding.rvEventsWishList) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = eventAdapter
        }
        subscribeUi(binding, eventAdapter)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentWishlistBinding, adapter: EventAdapter) {
//        viewModel.wishList.observe(viewLifecycleOwner, Observer { events ->
//            if (events.isNotEmpty()) {
//                binding.progressBar.visibility = View.GONE
//                eventAdapter.submitList(events)
//            }
//
//        })
    }
}