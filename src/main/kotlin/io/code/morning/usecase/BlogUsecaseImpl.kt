package io.code.morning.usecase

import io.code.morning.domain.Blog
import io.code.morning.domain.BlogBuilder
import io.code.morning.infrastructure.BlogRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BlogUsecaseImpl(
    private val blogBuilder: BlogBuilder,
    private val blogRepository: BlogRepository
) : BlogUsecase {

  override fun findList(): Mono<List<Blog>> {
    TODO("Not yet implemented")
  }

  override fun findById(id: String): Mono<Blog> =
      id
          .let { blogRepository.findById(it).orElse(null) }
          .let { blogBuilder.build(it) }

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