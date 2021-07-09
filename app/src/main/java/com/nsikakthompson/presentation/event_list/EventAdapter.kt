package com.nsikakthompson.presentation.event_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.databinding.EventItemBinding
import java.text.SimpleDateFormat

/**
 * Adapter for the [RecyclerView] in [LegoThemeFragment].
 */
class EventAdapter : PagedListAdapter<EventEntity, EventAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItem(position)
        holder.apply {
            bind(createOnClickListener(event!!), event!!)
            itemView.tag = event

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EventItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(item: EventEntity): View.OnClickListener {
        return View.OnClickListener {
            val direction =  EventFragmentDirections.actionEventFragmentToEventDetailsFragment(item.id)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: EventItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val INPUT_DATE_FORMAT= "yyyy-MM-dd'T'HH:mm:ss'Z'"
        @SuppressLint("SimpleDateFormat")
        fun bind(listener: View.OnClickListener, item: EventEntity) {
            binding.apply {
                clickListener = listener
                events = item
                eventDate = SimpleDateFormat("EEE, d MMM yyyy").format(SimpleDateFormat(INPUT_DATE_FORMAT).parse(item.startDateTime))
                eventTime = "${SimpleDateFormat("hh:mm:ss a").format(SimpleDateFormat(INPUT_DATE_FORMAT).parse(item.startDateTime))} - ${SimpleDateFormat("hh:mm:ss a").format(SimpleDateFormat(INPUT_DATE_FORMAT).parse(item.endDateTime))}"
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<EventEntity>() {

    override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
        return oldItem == newItem
    }
}
