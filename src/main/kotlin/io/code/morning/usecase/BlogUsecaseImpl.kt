package io.code.morning.usecase

import io.code.morning.domain.Blog
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BlogUsecaseImpl : BlogUsecase {

  override fun findList(): Mono<List<Blog>> {
    TODO("Not yet implemented")
  }

  override fun findById(id: String): Mono<Blog> {
    TODO("Not yet implemented")
  }

  override fun create(blog: Blog): Mono<Blog> {
    TODO("Not yet implemented")
  }

  override fun update(id: String, blog: Blog): Mono<Blog> {
    TODO("Not yet implemented")
  }

  override fun delete(id: String): Mono<Blog> {
    TODO("Not yet implemented")
  }
}