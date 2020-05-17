package io.code.morning.usecase

import io.code.morning.domain.Blog
import io.code.morning.domain.BlogBuilder
import io.code.morning.infrastructure.BlogEntity
import io.code.morning.infrastructure.BlogId
import io.code.morning.infrastructure.dynamodb.BlogRepository
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

  override fun create(blog: BlogEntity): Mono<String> =
      blog.let {
        blogRepository.save(BlogEntity(
            category = it.category,
            title = it.title,
            detail = it.detail
        ))
      }.let { Mono.just(it.id!!) }

  override fun update(blog: BlogEntity): Mono<Blog> {
    TODO("Not yet implemented")
  }

  override fun delete(id: String): Mono<Blog> {
    TODO("Not yet implemented")
  }
}