package io.code.morning.controller

data class BlogUpdateRequest(
    val category: String? = null,
    val title: String? = null,
    val detail: String? = null
)
