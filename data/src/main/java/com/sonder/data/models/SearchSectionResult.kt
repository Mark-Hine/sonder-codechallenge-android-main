package com.sonder.data.models

data class SearchSectionResult(
    val sectionTitle: String,
    val sectionDescription: String?,
    val items: List<SearchItem>
)

/**
 * Applies filters to the [SearchSectionResult] based on the [SearchRequestParams].
 */
fun SearchSectionResult.applyParamFilters(params: SearchRequestParams): SearchSectionResult {
    // Filter the items based on the content types and limit the number of items by the params size
    val filteredItems = items.filter { item ->
        item.contentType != null && params.contentTypes.contains(item.contentType.value)
    }.take(params.size)

    // Return a new search section result with the filtered items
    return copy(items = filteredItems)
}