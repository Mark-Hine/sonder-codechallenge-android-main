package com.sonder.codechallenge.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sonder.codechallenge.databinding.FragmentMainBinding
import com.sonder.codechallenge.utils.repeatOnLifecycleWhenResumed
import com.sonder.data.models.SearchAction
import com.sonder.data.models.SearchActionType
import com.sonder.data.models.SearchItemViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), SearchRecyclerAdapter.OnSearchItemClickListener {

	companion object {
		fun newInstance(searchItemViewType: SearchItemViewType): MainFragment {
			return MainFragment().apply {
				arguments = Bundle().apply {
					putSerializable(SEARCH_ITEM_VIEW_TYPE, searchItemViewType)
				}
			}
		}
	}

	private lateinit var searchAdapter: SearchRecyclerAdapter
	private lateinit var binding: FragmentMainBinding
	private val viewModel: MainFragmentViewModel by viewModels()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = FragmentMainBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setUpRecyclerView()
		observeViewModel()
		viewModel.onCreate()
	}

	private fun setUpRecyclerView() {
		val orientation = getRecyclerViewOrientation(viewModel.searchItemViewType)
		val layoutManager = LinearLayoutManager(requireContext(), orientation, false)
		searchAdapter = SearchRecyclerAdapter(viewModel.searchItemViewType, this)
		binding.searchSectionRecyclerView.layoutManager = layoutManager
		binding.searchSectionRecyclerView.adapter = searchAdapter
	}

	private fun observeViewModel() {
		viewLifecycleOwner.repeatOnLifecycleWhenResumed {
			viewModel.state.collect { updateView(it) }
		}
	}

	private fun updateView(state: SearchFragmentStates) {
		when (state) {
			is SearchFragmentStates.Started -> Unit
			is SearchFragmentStates.Loaded -> {
				binding.searchSectionTitle.text = state.sectionTitle
				binding.searchSectionDescription.text = state.sectionDescription
				searchAdapter.submitList(state.adapterItems)
			}
		}
	}

	@RecyclerView.Orientation
	private fun getRecyclerViewOrientation(searchItemViewType: SearchItemViewType): Int {
		return when (searchItemViewType) {
			SearchItemViewType.HORIZONTAL_COMPACT,
			SearchItemViewType.HORIZONTAL_DETAILED,
				-> RecyclerView.HORIZONTAL

			SearchItemViewType.VERTICAL_COMPACT,
			SearchItemViewType.VERTICAL_DETAILED,
				-> RecyclerView.VERTICAL
		}
	}

	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	 * OnSearchItemClickListener
	 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

	override fun onSearchItemClick(action: SearchAction) {
		when (action.type) {
			SearchActionType.ARTICLE,
			SearchActionType.CATEGORY_HUB,
				-> {
				// Open the scheme in the browser
				val uri = action.scheme.toUri()
				startActivity(Intent(Intent.ACTION_VIEW, uri))
			}

			else -> {
				// Display action scheme in a toast
				Toast.makeText(requireContext(), action.scheme, Toast.LENGTH_SHORT).show()
			}
		}
	}

}