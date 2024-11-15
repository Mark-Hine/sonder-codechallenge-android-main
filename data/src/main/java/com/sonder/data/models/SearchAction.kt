package com.sonder.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchAction(
    val scheme: String,
    val title: String? = null,
    val type: SearchActionType
) : Parcelable