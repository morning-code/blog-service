package io.code.morning.usecase

import io.code.morning.domain.Blog
import io.code.morning.infrastructure.BlogEntity
import io.code.morning.infrastructure.BlogId
import reactor.core.publisher.Mono

interface BlogUsecase {

  fun findList(): Mono<List<Blog>>

  fun findById(blogId: BlogId): Mono<Blog>

  fun create(blog: BlogEntity): Mono<Blog>

  fun update(blogId: BlogId, blog: BlogEntity): Mono<Blog>

  fun delete(blogId: BlogId)
}