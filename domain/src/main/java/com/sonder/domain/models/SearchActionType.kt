package com.sonder.domain.models

enum class SearchActionType(val value: String) {
    ARTICLE("article"),
    CATEGORY_HUB("category-hub"),
    SERIES("series"),
    DEEPLINK("deeplink"),
    ASSESSMENT("assessment"),
    PHONE_CALL("phone-call")
}
