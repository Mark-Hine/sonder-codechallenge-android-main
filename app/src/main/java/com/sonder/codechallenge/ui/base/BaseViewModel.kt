package com.sonder.codechallenge.ui.base

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * Base class for ViewModels that manage a single state object.
 *
 * The state is stored in a [SavedStateHandle], ensuring it is persisted across configuration changes.
 *
 * @param initialState The initial state of the ViewModel.
 * @param savedStateHandle The [SavedStateHandle] used to store and manage the state.
 */
abstract class BaseViewModel<State : Parcelable>(
	initialState: State,
	private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

	val state = savedStateHandle.getStateFlow(STATE_FLOW_KEY, initialState)

	/**
	 * Updates the state of this ViewModel.
	 *
	 * @param newState The new state.
	 */
	protected fun updateState(newState: State) {
		savedStateHandle[STATE_FLOW_KEY] = newState
	}

}

private const val STATE_FLOW_KEY = "state"