package io.code.morning.infrastructure

import org.springframework.data.repository.CrudRepository

interface BlogRepository : CrudRepository<BlogEntity, String> {
}