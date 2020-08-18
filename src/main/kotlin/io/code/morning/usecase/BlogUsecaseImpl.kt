package io.code.morning.usecase

import io.code.morning.domain.BlogBuilder
import io.code.morning.exceptions.BlogNotFoundException
import io.code.morning.domain.BlogEntity
import io.code.morning.infrastructure.BlogId
import io.code.morning.infrastructure.dynamodb.BlogCrudRepository
import io.code.morning.infrastructure.dynamodb.BlogPageRepository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono


@Service
class BlogUsecaseImpl(
    private val blogBuilder: BlogBuilder,
    private val blogCrudRepository: BlogCrudRepository,
    private val blogPageRepository: BlogPageRepository
) : BlogUsecase {

  override fun findList(pageable: Pageable): Mono<List<BlogEntity>> =
      Mono.just(blogPageRepository.findAll(pageable)
          .map {
            BlogEntity(
                id = BlogId(it.id!!),
                category = it.category,
                title = it.title,
                summary = it.summary,
                detail = it.detail,
                createdDate = it.createdDate,
                updatedDate = it.updatedDate
            )
          }
          .toList())

  override fun findById(blogId: BlogId): Mono<BlogEntity> =
      blogId
          .let {
            blogCrudRepository.findById(it.id)
                .orElseThrow { BlogNotFoundException("blog not found. id: $it") }
          }
          .let {
            Mono.just(BlogEntity(
                id = BlogId(it.id!!),
                category = it.category,
                title = it.title,
                summary = it.summary,
                detail = it.detail,
                createdDate = it.createdDate,
                updatedDate = it.updatedDate
            ))
          }

  override fun create(blog: BlogEntity): Mono<BlogEntity> =
      blog.let {
        blogCrudRepository.save(blogBuilder.build(it))
      }.let {
        Mono.just(BlogEntity(
            id = BlogId(it.id!!),
            category = it.category,
            title = it.title,
            summary = it.summary,
            detail = it.detail,
            createdDate = it.createdDate,
            updatedDate = it.updatedDate
        ))
      }

  override fun update(blog: BlogEntity): Mono<BlogEntity> =
      blog.let {
        blogCrudRepository.save(blogBuilder.build(it))
      }.let {
        Mono.just(BlogEntity(
            id = BlogId(it.id!!),
            category = it.category,
            title = it.title,
            summary = it.summary,
            detail = it.detail,
            createdDate = it.createdDate,
            updatedDate = it.updatedDate
        ))
      }


  override fun delete(blogId: BlogId) = blogCrudRepository.deleteById(blogId.id)
}