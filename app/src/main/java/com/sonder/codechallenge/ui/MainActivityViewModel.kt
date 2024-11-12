package com.sonder.codechallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonder.domain.usecases.search.ClearSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val clearSearchResultsUseCase: ClearSearchResultsUseCase
) : ViewModel() {

    // Define activity states class
//    private val _state: MutableStateFlow<SearchActivityStates> = MutableStateFlow(SearchActivityStates.Started)
//    val state = _state.asStateFlow()

    fun onCreate() {
        // Started state
    }

    fun updateSearchQuery(query: String) {
        viewModelScope.launch {
            clearSearchResults()
        }
    }

    fun clearSearchQuery() {
        clearSearchResults()
        // Emit Started State
    }

    private fun clearSearchResults() {
        viewModelScope.launch {
            clearSearchResultsUseCase.execute(Unit)
        }
    }

}