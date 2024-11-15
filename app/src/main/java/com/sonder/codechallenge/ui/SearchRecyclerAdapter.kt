package com.sonder.codechallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.sonder.codechallenge.databinding.ItemHorizontalCompactBinding
import com.sonder.codechallenge.databinding.ItemHorizontalDetailedBinding
import com.sonder.codechallenge.databinding.ItemVerticalCompactBinding
import com.sonder.codechallenge.databinding.ItemVerticalDetailedBinding
import com.sonder.data.models.SearchAction
import com.sonder.data.models.SearchItem
import com.sonder.data.models.SearchItemViewType

class SearchRecyclerAdapter(
	private val searchItemViewType: SearchItemViewType,
	private val clickListener: OnSearchItemClickListener,
) : ListAdapter<SearchItem, SearchRecyclerAdapter.ViewHolder>(DIFF_CALLBACK) {

	/**
	 * Interface for handling item clicks.
	 */
	interface OnSearchItemClickListener {

		/**
		 * Called when a [SearchItem] is clicked.
		 *
		 * @param action The action data.
		 */
		fun onSearchItemClick(action: SearchAction)
	}

	companion object {

		const val VIEW_TYPE_HORIZONTAL_COMPACT = 1
		const val VIEW_TYPE_VERTICAL_COMPACT = 2
		const val VIEW_TYPE_HORIZONTAL_DETAILED = 3
		const val VIEW_TYPE_VERTICAL_DETAILED = 4

		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchItem>() {

			override fun areItemsTheSame(
				oldItem: SearchItem,
				newItem: SearchItem,
			): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(
				oldItem: SearchItem,
				newItem: SearchItem,
			): Boolean {
				return oldItem == newItem
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return when (viewType) {
			VIEW_TYPE_HORIZONTAL_COMPACT -> {
				val binding = ItemHorizontalCompactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
				HorizontalCompactViewHolder(binding)
			}

			VIEW_TYPE_VERTICAL_COMPACT -> {
				val binding = ItemVerticalCompactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
				VerticalCompactViewHolder(binding)
			}

			VIEW_TYPE_HORIZONTAL_DETAILED -> {
				val binding = ItemHorizontalDetailedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
				HorizontalDetailedViewHolder(binding)
			}

			VIEW_TYPE_VERTICAL_DETAILED -> {
				val binding = ItemVerticalDetailedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
				VerticalDetailedViewHolder(binding)
			}

			else -> throw IllegalArgumentException("Unhandled viewType: $viewType")
		}
	}

	override fun getItemViewType(position: Int): Int {
		return when (searchItemViewType) {
			SearchItemViewType.HORIZONTAL_COMPACT -> VIEW_TYPE_HORIZONTAL_COMPACT
			SearchItemViewType.VERTICAL_COMPACT -> VIEW_TYPE_VERTICAL_COMPACT
			SearchItemViewType.HORIZONTAL_DETAILED -> VIEW_TYPE_HORIZONTAL_DETAILED
			SearchItemViewType.VERTICAL_DETAILED -> VIEW_TYPE_VERTICAL_DETAILED
		}
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = getItem(position) ?: return
		holder.bind(item)
	}

	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	 * ViewHolders
	 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

	abstract inner class ViewHolder(
		binding: ViewBinding,
	) : RecyclerView.ViewHolder(binding.root) {

		init {
			binding.root.setOnClickListener {
				val item = getItem(bindingAdapterPosition)
				val action = item.action
				if (action != null) {
					clickListener.onSearchItemClick(action)
				}
			}
		}

		abstract fun bind(item: SearchItem)
	}

	inner class HorizontalCompactViewHolder(
		private val binding: ItemHorizontalCompactBinding,
	) : ViewHolder(binding) {
		override fun bind(item: SearchItem) {
			binding.searchItemTitle.text = item.title
		}
	}

	inner class VerticalCompactViewHolder(
		private val binding: ItemVerticalCompactBinding,
	) : ViewHolder(binding) {
		override fun bind(item: SearchItem) {
			binding.searchItemTitle.text = item.title
		}
	}

	inner class HorizontalDetailedViewHolder(
		private val binding: ItemHorizontalDetailedBinding,
	) : ViewHolder(binding) {
		override fun bind(item: SearchItem) {
			binding.searchItemTitle.text = item.title
			binding.searchItemDescription.text = item.description
			binding.searchItemCtaButton.text = item.action?.title
			binding.searchItemContentType.text = item.contentType?.value

			// Load thumbnail
			Glide.with(itemView.context)
				.load(item.thumbnail)
				.into(binding.searchItemImage)
		}
	}

	inner class VerticalDetailedViewHolder(
		private val binding: ItemVerticalDetailedBinding,
	) : ViewHolder(binding) {
		override fun bind(item: SearchItem) {
			binding.searchItemTitle.text = item.title
			binding.searchItemDescription.text = item.description
			binding.searchItemContentType.text = item.contentType?.value

			// Load thumbnail
			Glide.with(itemView.context)
				.load(item.thumbnail)
				.into(binding.searchItemImage)
		}
	}
}