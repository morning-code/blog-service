package io.code.morning.usecase

import io.code.morning.domain.Blog
import io.code.morning.infrastructure.BlogEntity
import io.code.morning.infrastructure.BlogId
import reactor.core.publisher.Mono

interface BlogUsecase {

  fun findList(): Mono<List<Blog>>

  fun findById(id: String): Mono<Blog>

  fun create(blog: BlogEntity): Mono<BlogId>

  fun update(blog: BlogEntity): Mono<Blog>

  fun delete(id: String): Mono<Blog>
}