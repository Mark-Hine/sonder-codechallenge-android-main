package com.sonder.codechallenge.ui

import com.sonder.data.models.SearchItemViewType

/**
 * Represents the different states of the search activity.
 */
sealed interface SearchActivityStates {

	/**
	 * Represents the started state of the search activity.
	 */
	data object Started : SearchActivityStates

	/**
	 * The search activity is loading with the given query.
	 */
	data class Loading(
		val query: String,
	) : SearchActivityStates

	/**
	 * The search activity has loaded with the given query and sections.
	 */
	data class Loaded(
		val query: String,
		val searchItemViewTypes: List<SearchItemViewType>,
	) : SearchActivityStates

	/**
	 * The search activity has encountered an error.
	 */
	data object Error : SearchActivityStates
}
