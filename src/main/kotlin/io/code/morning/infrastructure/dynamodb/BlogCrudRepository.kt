package io.code.morning.infrastructure.dynamodb

import org.springframework.data.repository.CrudRepository

interface BlogCrudRepository : CrudRepository<BlogDto, String> {
}