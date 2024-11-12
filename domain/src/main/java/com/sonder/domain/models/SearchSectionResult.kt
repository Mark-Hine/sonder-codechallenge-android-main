package com.sonder.domain.models

data class SearchSectionResult(
    val sectionTitle: String,
    val sectionDescription: String?,
    val items: List<SearchItem>
)