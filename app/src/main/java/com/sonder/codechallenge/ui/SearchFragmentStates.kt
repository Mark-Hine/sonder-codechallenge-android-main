package com.sonder.codechallenge.ui

import android.os.Parcelable
import com.sonder.data.models.SearchItem
import com.sonder.data.models.SearchItemViewType
import kotlinx.parcelize.Parcelize

/**
 * Represents the states of the search fragment.
 */
sealed interface SearchFragmentStates : Parcelable {

	/**
	 * Represents the started state of the search fragment.
	 */
	@Parcelize
	data object Started : SearchFragmentStates

	/**
	 * The search fragment loaded with the given data.
	 */
	@Parcelize
	data class Loaded(
		val sectionTitle: String,
		val sectionDescription: String?,
		val searchItemViewType: SearchItemViewType,
		val adapterItems: List<SearchItem>,
	) : SearchFragmentStates
}