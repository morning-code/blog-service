package io.code.morning.usecase

import io.code.morning.domain.BlogEntity
import io.code.morning.infrastructure.BlogId
import reactor.core.publisher.Mono

interface BlogUsecase {

  fun findList(): Mono<List<BlogEntity>>

  fun findById(blogId: BlogId): Mono<BlogEntity>

  fun create(blog: BlogEntity): Mono<BlogEntity>

  fun update(blogId: BlogId, blog: BlogEntity): Mono<BlogEntity>

  fun delete(blogId: BlogId)
}