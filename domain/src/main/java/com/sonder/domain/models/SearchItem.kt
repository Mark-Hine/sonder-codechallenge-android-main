package com.sonder.domain.models

data class SearchItem(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val contentType: SearchContentType? = null,
    val thumbnail: String? = null,
    val action: SearchAction? = null
)

