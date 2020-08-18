package io.code.morning.domain

import io.code.morning.infrastructure.BlogId


data class BlogEntity(
    var id: BlogId,
    var category: String? = null,
    var title: String? = null,
    var summary: String? = null,
    var detail: String? = null,
    var createdDate: String? = null,
    var updatedDate: String? = null
)
