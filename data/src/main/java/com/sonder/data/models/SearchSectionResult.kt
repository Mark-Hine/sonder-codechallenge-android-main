package com.sonder.data.models

data class SearchSectionResult(
    val sectionTitle: String,
    val sectionDescription: String?,
    val items: List<SearchItem>
)