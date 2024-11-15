package com.sonder.data

import com.sonder.data.models.SearchContentType
import com.sonder.data.models.SearchRequestParams

object MockRequests {

	val errorRequestParams = SearchRequestParams(
		sectionTitle = "Resources",
		query = "error",
		size = 10,
		contentTypes = listOf(
			SearchContentType.ARTICLE.value,
			SearchContentType.CALL_PATHWAY.value
		)
	)

	val horizontalCompactRequestParams = SearchRequestParams(
		sectionTitle = "Topics",
		query = "mock",
		size = 3,
		contentTypes = listOf(SearchContentType.CATEGORY_HUB.value)
	)

	val verticalCompactRequestParams = SearchRequestParams(
		sectionTitle = "Categories",
		query = "mock",
		size = 4,
		contentTypes = listOf(
			SearchContentType.ARTICLE.value,
			SearchContentType.CATEGORY_HUB.value,
			SearchContentType.ACTION.value
		)
	)

	val horizontalDetailedRequestParams = SearchRequestParams(
		sectionTitle = "Tools and services",
		query = "mock",
		size = 6,
		contentTypes = listOf(
			SearchContentType.ASSESSMENT.value,
			SearchContentType.ACTION.value,
			SearchContentType.CALL_PATHWAY.value,
			SearchContentType.SERIES.value,
			SearchContentType.ARTICLE.value
		)
	)

	val verticalDetailedRequestParams = SearchRequestParams(
		sectionTitle = "Resources",
		query = "mock",
		size = 10,
		contentTypes = listOf(
			SearchContentType.ARTICLE.value,
			SearchContentType.CALL_PATHWAY.value
		)
	)

}