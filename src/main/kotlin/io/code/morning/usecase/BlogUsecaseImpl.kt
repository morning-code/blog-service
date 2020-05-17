package io.code.morning.usecase

import io.code.morning.domain.Blog
import io.code.morning.domain.BlogBuilder
import io.code.morning.exceptions.BlogNotFoundException
import io.code.morning.infrastructure.BlogEntity
import io.code.morning.infrastructure.BlogId
import io.code.morning.infrastructure.dynamodb.BlogRepository
import org.springframework.http.HttpStatus
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
          .let {
            blogRepository.findById(it)
                .orElseThrow { BlogNotFoundException("blog not found. id: $id") }
          }
          .let { blogBuilder.build(it) }

  override fun create(blog: BlogEntity): Mono<Blog> =
      blog.let {
        blogRepository.save(BlogEntity(
            category = it.category,
            title = it.title,
            detail = it.detail
        ))
      }.let { blogBuilder.build(it) }

  override fun update(id: String, blog: BlogEntity): Mono<Blog> =
      blog.let {
        blogRepository.save(BlogEntity(
            id = id,
            category = it.category,
            title = it.title,
            detail = it.detail
        ))
      }.let { blogBuilder.build(it) }

  override fun delete(id: String) = blogRepository.deleteById(id)
}