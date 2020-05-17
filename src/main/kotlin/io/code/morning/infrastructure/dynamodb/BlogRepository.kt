package io.code.morning.infrastructure.dynamodb

import io.code.morning.infrastructure.BlogEntity
import org.springframework.data.repository.CrudRepository

interface BlogRepository : CrudRepository<BlogEntity, String> {
}