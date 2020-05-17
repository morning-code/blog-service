package io.code.morning.usecase

import io.code.morning.domain.BlogBuilder
import io.code.morning.exceptions.BlogNotFoundException
import io.code.morning.domain.BlogEntity
import io.code.morning.infrastructure.BlogId
import io.code.morning.infrastructure.dynamodb.BlogRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BlogUsecaseImpl(
    private val blogBuilder: BlogBuilder,
    private val blogRepository: BlogRepository
) : BlogUsecase {

  override fun findList(): Mono<List<BlogEntity>> {
    TODO("Not yet implemented")
  }

  override fun findById(blogId: BlogId): Mono<BlogEntity> =
      blogId
          .let {
            blogRepository.findById(it.id)
                .orElseThrow { BlogNotFoundException("blog not found. id: $it") }
          }
          .let {
            Mono.just(BlogEntity(
                id = BlogId(it.id!!),
                category = it.category,
                title = it.title,
                detail = it.detail
            ))
          }

  override fun create(blog: BlogEntity): Mono<BlogEntity> =
      blog.let {
        blogRepository.save(blogBuilder.build(it))
      }.let {
        Mono.just(BlogEntity(
            id = BlogId(it.id!!),
            category = it.category,
            title = it.title,
            detail = it.detail
        ))
      }

  override fun update(blogId: BlogId, blog: BlogEntity): Mono<BlogEntity> =
      blog.let {
        blogRepository.save(blogBuilder.build(it))
      }.let {
        Mono.just(BlogEntity(
            id = BlogId(it.id!!),
            category = it.category,
            title = it.title,
            detail = it.detail
        ))
      }


  override fun delete(blogId: BlogId) = blogRepository.deleteById(blogId.id)
}