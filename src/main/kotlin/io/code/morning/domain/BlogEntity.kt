package io.code.morning.domain

import io.code.morning.infrastructure.BlogId


data class BlogEntity(
    var id: BlogId? = null,
    var category: String? = null,
    var title: String? = null,
    var detail: String? = null
)
