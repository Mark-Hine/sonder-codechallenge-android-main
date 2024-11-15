package com.sonder.codechallenge.ui

import com.sonder.data.models.SearchItem
import com.sonder.data.models.SearchItemViewType

/**
 * Represents the states of the search fragment.
 */
sealed interface SearchFragmentStates {

	/**
	 * Represents the started state of the search fragment.
	 */
	data object Started : SearchFragmentStates

	/**
	 * The search fragment loaded with the given data.
	 */
	data class Loaded(
		val sectionTitle: String,
		val sectionDescription: String?,
		val searchItemViewType: SearchItemViewType,
		val adapterItems: List<SearchItem>,
	) : SearchFragmentStates
}