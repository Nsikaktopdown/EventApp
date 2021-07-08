package com.nsikakthompson.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.databinding.EventItemBinding

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
//            val direction = LegoThemeFragmentDirections.actionThemeFragmentToSetsFragment(id, name)
//            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: EventItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: EventEntity) {
            binding.apply {
                clickListener = listener
                events = item
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
