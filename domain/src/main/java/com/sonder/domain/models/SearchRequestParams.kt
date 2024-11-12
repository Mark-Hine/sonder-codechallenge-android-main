package com.sonder.domain.models

data class SearchRequestParams(
    val sectionTitle: String,
    val query: String,
    val size: Int = 10,
    val contentTypes: List<String>
)
