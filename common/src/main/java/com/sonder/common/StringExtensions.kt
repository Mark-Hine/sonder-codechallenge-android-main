package com.sonder.common

fun String?.safeString(default: String = ""): String {
    return if (this.isNullOrEmpty()) {
        default
    } else {
        this
    }
}