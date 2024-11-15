package com.sonder.data.models

data class SearchAction(
    val scheme: String,
    val title: String? = null,
    val type: SearchActionType
)