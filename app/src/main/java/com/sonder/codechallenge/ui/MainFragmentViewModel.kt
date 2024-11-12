package com.sonder.codechallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonder.domain.usecases.search.SubscribeToSearchQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val subscribeToSearchQueryUseCase: SubscribeToSearchQueryUseCase
) : ViewModel() {

    private var query: String? = null

    // Define fragment states class
//    private val _state: MutableStateFlow<SearchFragmentStates> = MutableStateFlow(SearchFragmentStates.Started)
//    val state = _state.asStateFlow()

    fun onCreate() {
        viewModelScope.launch {
            subscribeToSearchQueryUseCase.execute(Unit).collect {
                if (it.isNotEmpty()) {
                    query = it
                }
            }
        }
    }

    fun getSectionSearchResults(sectionTitle: String?) {
        sectionTitle?.let { title ->
            viewModelScope.launch {
                // Load results
            }
        }
    }
}