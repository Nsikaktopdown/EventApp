package com.nsikakthompson.presentation.event_list

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsikakthompson.R
import com.nsikakthompson.databinding.FragmentEventsBinding
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

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        with(binding.rvEvents) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = eventAdapter
        }

        subscribeUi(binding)

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

    private fun subscribeUi(binding: FragmentEventsBinding) {
        viewModel.events.observe(viewLifecycleOwner, Observer { events ->
            if (events.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
                eventAdapter.submitList(events)
            }

        })

        viewModel.wishCount.observe(viewLifecycleOwner, Observer {
          binding.countLayout.tvCount.visibility = if(it == 0)  View.GONE else View.VISIBLE
            binding.countLayout.tvCount.text = it.toString()

        })

    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_event, menu)
//        val item = menu.findItem(R.id.counter)
//        item.setActionView(R.layout.wishlist_counter)
//        val parent = item.actionView as ConstraintLayout
//        val countView = parent.findViewById<TextView>(R.id.tvCount)
//        viewModel.wishCount.observe(viewLifecycleOwner, Observer {
//            countView.visibility = if(it == 0)  View.GONE else View.VISIBLE
//            countView.text = it.toString()
//            activity?.invalidateOptionsMenu()
//        })
//
//        countView.setOnClickListener {
//           // onOptionsItemSelected(item)
//            Timber.e("you are clicking me")
//        }
//
//
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.counter -> {
//                Timber.e("you are clicking me")
//                findNavController(this).navigate(EventFragmentDirections.actionEventFragmentToWishListFragment())
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//
//    }
}