package com.sonder.codechallenge.ui

import android.os.Parcelable
import com.sonder.data.models.SearchItemViewType
import kotlinx.parcelize.Parcelize

/**
 * Represents the different states of the search activity.
 */
sealed interface SearchActivityStates : Parcelable {

	/**
	 * Represents the started state of the search activity.
	 */
	@Parcelize
	data object Started : SearchActivityStates

	/**
	 * The search activity is loading with the given query.
	 */
	@Parcelize
	data class Loading(
		val query: String,
	) : SearchActivityStates

	/**
	 * The search activity has loaded with the given query and sections.
	 */
	@Parcelize
	data class Loaded(
		val query: String,
		val searchItemViewTypes: List<SearchItemViewType>,
	) : SearchActivityStates

	/**
	 * The search activity has encountered an error.
	 */
	@Parcelize
	data object Error : SearchActivityStates
}
