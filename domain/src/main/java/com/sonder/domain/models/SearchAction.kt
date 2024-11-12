package com.sonder.domain.models

data class SearchAction(
    val scheme: String,
    val title: String? = null,
    val type: SearchActionType
)