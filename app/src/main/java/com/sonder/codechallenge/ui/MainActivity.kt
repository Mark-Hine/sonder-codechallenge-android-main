package com.sonder.codechallenge.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.sonder.codechallenge.R
import com.sonder.codechallenge.databinding.ActivityMainBinding
import com.sonder.codechallenge.utils.repeatOnLifecycleWhenResumed
import com.sonder.common.safeString
import com.sonder.data.models.SearchItemViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding
	private val viewModel: MainActivityViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		bindViewActions()
		observeViewModel()
	}

	private fun bindViewActions() {
		setSupportActionBar(binding.searchToolbar)
		binding.apply {
			etSearchToolbar.setOnQueryTextListener(createQueryTextListener())
		}
	}

	private fun observeViewModel() {
		repeatOnLifecycleWhenResumed {
			viewModel.state.collect { updateView(it) }
		}
	}

	private fun updateView(state: SearchActivityStates) {
		// Update views
		binding.searchProgressBar.isVisible = state is SearchActivityStates.Loading
		binding.tvSearchTitle.text = getSearchTitleText(state)

		// Update fragments
		removeAllFragments()
		if (state is SearchActivityStates.Loaded) {
			addFragments(state.searchItemViewTypes)
		}
	}

	private fun getSearchTitleText(state: SearchActivityStates): String {
		return when (state) {
			is SearchActivityStates.Started -> {
				getString(R.string.initial_search_results_message)
			}

			is SearchActivityStates.Loading -> {
				getString(R.string.loading_search_results_message, state.query)
			}

			is SearchActivityStates.Loaded -> {
				if (state.searchItemViewTypes.isEmpty()) {
					getString(R.string.empty_search_results_message)
				} else {
					getString(R.string.loaded_search_results_message, state.query)
				}
			}

			is SearchActivityStates.Error -> {
				getString(R.string.error_search_results_message)
			}
		}
	}

	private fun removeAllFragments() {
		val fragmentTransaction = supportFragmentManager.beginTransaction()
		supportFragmentManager.fragments.forEach { fragment ->
			fragmentTransaction.remove(fragment)
		}
		fragmentTransaction.commit()
	}

	private fun addFragments(searchItemViewTypes: List<SearchItemViewType>) {
		val fragmentTransaction = supportFragmentManager.beginTransaction()
		searchItemViewTypes.forEach { searchItemViewType ->
			if (supportFragmentManager.findFragmentByTag(searchItemViewType.value) == null) {
				val fragment = MainFragment.newInstance(searchItemViewType)
				fragmentTransaction.add(R.id.searchFragmentContainer, fragment, searchItemViewType.value)
			}
		}
		fragmentTransaction.commit()
	}

	private fun createQueryTextListener() = object : SearchView.OnQueryTextListener {
		override fun onQueryTextSubmit(query: String?): Boolean {
			if (query.safeString().length > 2) {
				binding.etSearchToolbar.clearFocus()
				viewModel.updateSearchQuery(query.safeString())
			} else {
				viewModel.clearSearchQuery()
			}
			return false
		}

		override fun onQueryTextChange(newText: String?): Boolean {
			if (newText.isNullOrEmpty()) {
				viewModel.clearSearchQuery()
			}
			return false
		}
	}
}