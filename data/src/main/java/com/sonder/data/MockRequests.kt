package com.sonder.data

import com.sonder.domain.models.SearchContentType
import com.sonder.domain.models.SearchRequestParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MockRequests {

    fun getErrorRequestParams(): Flow<SearchRequestParams> = flow {
        val request = SearchRequestParams(
            sectionTitle = "Resources",
            query = "error",
            size = 10,
            contentTypes = listOf(
                SearchContentType.ARTICLE.value,
                SearchContentType.CALL_PATHWAY.value
            )
        )

        emit(request)
    }

    fun getHorizontalCompactRequestParams(): Flow<SearchRequestParams> = flow {
        val request = SearchRequestParams(
            sectionTitle = "Topics",
            query = "mock",
            size = 3,
            contentTypes = listOf(SearchContentType.CATEGORY_HUB.value)
        )

        emit(request)
    }

    fun getVerticalCompactRequestParams(): Flow<SearchRequestParams> = flow {
        val request = SearchRequestParams(
            sectionTitle = "Categories",
            query = "mock",
            size = 4,
            contentTypes = listOf(
                SearchContentType.ARTICLE.value,
                SearchContentType.CATEGORY_HUB.value,
                SearchContentType.ACTION.value
            )
        )

        emit(request)
    }

    fun getHorizontaDetailedRequestParams(): Flow<SearchRequestParams> = flow {
        val request = SearchRequestParams(
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

        emit(request)
    }

    fun getVerticalDetailedRequestParams(): Flow<SearchRequestParams> = flow {
        val request = SearchRequestParams(
            sectionTitle = "Resources",
            query = "mock",
            size = 10,
            contentTypes = listOf(
                SearchContentType.ARTICLE.value,
                SearchContentType.CALL_PATHWAY.value
            )
        )

        emit(request)
    }

}