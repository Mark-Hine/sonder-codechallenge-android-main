package com.sonder.data

import com.sonder.domain.models.SearchAction
import com.sonder.domain.models.SearchActionType
import com.sonder.domain.models.SearchContentType
import com.sonder.domain.models.SearchItem
import com.sonder.domain.models.SearchSectionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MockResponses {

    // Error result
    fun getErrorResult(): Flow<Exception> = flow {
        emit(Exception("404"))
    }

    fun getHorizontalCompactSearchResults(): Flow<SearchSectionResult> = flow {
        val searchItems = listOf(
            SearchItem(
                id = "1",
                contentType = SearchContentType.CATEGORY_HUB,
                title = "Mental Health",
                description = null,
                thumbnail = null,
                action = SearchAction(
                    title = "Mental Health",
                    type = SearchActionType.CATEGORY_HUB,
                    scheme = "https://www.who.int/health-topics/mental-health"
                )
            ),
            SearchItem(
                id = "2",
                contentType = SearchContentType.CATEGORY_HUB,
                title = "Safety",
                description = null,
                thumbnail = null,
                action = SearchAction(
                    title = "Safety",
                    type = SearchActionType.CATEGORY_HUB,
                    scheme = "https://www.health.gov.au/topics/mental-health-and-suicide-prevention/about-mental-health"
                )
            ),
            SearchItem(
                id = "3",
                contentType = SearchContentType.ACTION,
                title = "Start Chat now",
                description = "Start chat with Sonder",
                thumbnail = "https://github.githubassets.com/assets/globe-d6f3f4ee645a.jpg",
                action = SearchAction(
                    title = "Start Chat now",
                    type = SearchActionType.DEEPLINK,
                    scheme = "deeplink//chat"
                )
            ),
            SearchItem(
                id = "4",
                contentType = SearchContentType.CATEGORY_HUB,
                title = "Relationships",
                description = null,
                thumbnail = null,
                action = SearchAction(
                    title = "Relationships",
                    type = SearchActionType.CATEGORY_HUB,
                    scheme = "https://www.beyondblue.org.au/mental-health/what-is-mental-health"
                )
            )
        )

        emit(
            SearchSectionResult(
                sectionTitle = "Topics",
                sectionDescription = null,
                items = searchItems
            )
        )
    }

    fun getVerticalCompactSearchResults(): Flow<SearchSectionResult> = flow {
        emit(
            SearchSectionResult(
                sectionTitle = "Categories",
                sectionDescription = null,
                items = emptyList()
            )
        )
    }

    fun getHorizontalDetailedSectionResults(): Flow<SearchSectionResult> = flow {
        val searchItems = listOf(
            SearchItem(
                id = "1",
                contentType = SearchContentType.ACTION,
                title = "Start Chat",
                description = "Start chat with Sonder",
                thumbnail = "https://github.githubassets.com/assets/globe-d6f3f4ee645a.jpg",
                action = SearchAction(
                    title = "Start Chat",
                    type = SearchActionType.DEEPLINK,
                    scheme = "deeplink//chat"
                )
            ),
            SearchItem(
                id = "2",
                contentType = SearchContentType.CALL_PATHWAY,
                title = "Call for your mental health",
                description = "Discover what good mental health looks like and how to maintain it",
                thumbnail = "https://plus.unsplash.com/premium_photo-1683910767532-3a25b821f7ae",
                action = SearchAction(
                    title = "Read Article",
                    type = SearchActionType.PHONE_CALL,
                    scheme = "1800 123 456"
                )
            ),
            SearchItem(
                id = "3",
                contentType = SearchContentType.SERIES,
                title = "Relationships",
                description = "Check this series",
                thumbnail = "https://www.gstatic.com/webp/gallery3/1_webp_ll.sm.png",
                action = SearchAction(
                    title = "Open Series",
                    type = SearchActionType.SERIES,
                    scheme = "open-relationships-series"
                )
            ),
            SearchItem(
                id = "4",
                contentType = SearchContentType.ARTICLE,
                title = "Well Being",
                description = "Mental health is an important part of overall health and well-being. It affects how we think, feel, and act. It may also affect how we handle stress, relate to others, and make choices during an emergency. Mental health is important at every stage of life, from childhood and adolescence through adulthood",
                thumbnail = "https://images.unsplash.com/photo-1544894079-e81a9eb1da8b",
                action = SearchAction(
                    title = "Read Article on Mental Health",
                    type = SearchActionType.ARTICLE,
                    scheme = "https://www.beyondblue.org.au/mental-health/what-is-mental-health"
                )
            ),
            SearchItem(
                id = "5",
                contentType = SearchContentType.ASSESSMENT,
                title = "Wellbeing Assessment",
                description = "Take this confidential 3 minute assessment, and we’ll provide you with personalised support to boost your overall wellbeing",
                thumbnail = "https://images.unsplash.com/photo-1721132447246-5d33f3008b05",
                action = SearchAction(
                    title = "Check your wellbeing",
                    type = SearchActionType.ASSESSMENT,
                    scheme = "open-wellbeing-assessment"
                )
            ),
            SearchItem(
                id = "6",
                contentType = SearchContentType.ARTICLE,
                title = "Looking after your mental health",
                description = "Discover what good mental health looks like and how to maintain it",
                thumbnail = "https://images.unsplash.com/photo-1720884413532-59289875c3e1",
                action = SearchAction(
                    title = "Read Article now",
                    type = SearchActionType.ARTICLE,
                    scheme = "https://www.health.gov.au/topics/mental-health-and-suicide-prevention/about-mental-health"
                )
            )
        )

        emit(
            SearchSectionResult(
                sectionTitle = "Tools and services",
                sectionDescription = "All tools and services from Sonder",
                items = searchItems
            )
        )
    }

    fun getVerticalDetailedSectionResults(): Flow<SearchSectionResult> = flow {
        val searchItems = listOf(
            SearchItem(
                id = "1",
                contentType = SearchContentType.ACTION,
                title = "Start Chat",
                description = "Start chat with Sonder",
                thumbnail = "https://github.githubassets.com/assets/globe-d6f3f4ee645a.jpg",
                action = SearchAction(
                    title = "Start Chat",
                    type = SearchActionType.DEEPLINK,
                    scheme = "deeplink//chat"
                )
            ),
            SearchItem(
                id = "2",
                contentType = SearchContentType.CALL_PATHWAY,
                title = "Call for your mental health",
                description = "Discover what good mental health looks like and how to maintain it",
                thumbnail = "https://plus.unsplash.com/premium_photo-1683910767532-3a25b821f7ae",
                action = SearchAction(
                    title = "Read Article",
                    type = SearchActionType.PHONE_CALL,
                    scheme = "1800 123 456"
                )
            ),
            SearchItem(
                id = "3",
                contentType = SearchContentType.SERIES,
                title = "Relationships",
                description = "Check this series",
                thumbnail = "https://www.gstatic.com/webp/gallery3/1_webp_ll.sm.png",
                action = SearchAction(
                    title = "Open Series",
                    type = SearchActionType.SERIES,
                    scheme = "open-relationships-series"
                )
            ),
            SearchItem(
                id = "4",
                contentType = SearchContentType.ARTICLE,
                title = "Well Being",
                description = "Mental health is an important part of overall health and well-being. It affects how we think, feel, and act. It may also affect how we handle stress, relate to others, and make choices during an emergency. Mental health is important at every stage of life, from childhood and adolescence through adulthood",
                thumbnail = "https://images.unsplash.com/photo-1544894079-e81a9eb1da8b",
                action = SearchAction(
                    title = "Read Article on Mental Health",
                    type = SearchActionType.ARTICLE,
                    scheme = "https://www.beyondblue.org.au/mental-health/what-is-mental-health"
                )
            ),
            SearchItem(
                id = "5",
                contentType = SearchContentType.ASSESSMENT,
                title = "Wellbeing Assessment",
                description = "Take this confidential 3 minute assessment, and we’ll provide you with personalised support to boost your overall wellbeing",
                thumbnail = "https://images.unsplash.com/photo-1721132447246-5d33f3008b05",
                action = SearchAction(
                    title = "Check your wellbeing",
                    type = SearchActionType.ASSESSMENT,
                    scheme = "open-wellbeing-assessment"
                )
            ),
            SearchItem(
                id = "6",
                contentType = SearchContentType.ARTICLE,
                title = "Looking after your mental health",
                description = "Discover what good mental health looks like and how to maintain it",
                thumbnail = "https://images.unsplash.com/photo-1720884413532-59289875c3e1",
                action = SearchAction(
                    title = "Read Article now",
                    type = SearchActionType.ARTICLE,
                    scheme = "https://www.health.gov.au/topics/mental-health-and-suicide-prevention/about-mental-health"
                )
            )
        )

        emit(
            SearchSectionResult(
                sectionTitle = "Resources",
                sectionDescription = null,
                items = searchItems
            )
        )
    }

}