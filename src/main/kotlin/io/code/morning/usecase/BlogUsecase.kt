package io.code.morning.usecase

import io.code.morning.domain.Blog
import reactor.core.publisher.Mono

interface BlogUsecase {

  fun findList(): Mono<List<Blog>>

  fun findById(id: String): Mono<Blog>

  fun create(blog: Blog): Mono<Blog>

  fun update(id: String, blog: Blog): Mono<Blog>

  fun delete(id: String): Mono<Blog>
}