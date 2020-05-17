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

  override fun findById(blogId: BlogId): Mono<Blog> =
      blogId
          .let {
            blogRepository.findById(it.id)
                .orElseThrow { BlogNotFoundException("blog not found. id: $it") }
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

  override fun update(blogId: BlogId, blog: BlogEntity): Mono<Blog> =
      blog.let {
        blogRepository.save(BlogEntity(
            id = blogId.id,
            category = it.category,
            title = it.title,
            detail = it.detail
        ))
      }.let { blogBuilder.build(it) }

  override fun delete(blogId: BlogId) = blogRepository.deleteById(blogId.id)
}