package io.code.morning.domain

import java.sql.Timestamp

data class Blog(
    val id: String,
    val category: String,
    val title: String,
    val detail: String,
    val createdBy: String,
    val createdAt: Timestamp,
    val updatedBy: String,
    val updatedAt: Timestamp
)
