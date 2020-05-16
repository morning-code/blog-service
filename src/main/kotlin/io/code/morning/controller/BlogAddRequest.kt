package io.code.morning.controller

data class BlogAddRequest(
    val category: String,
    val title: String,
    val detail: String
)
