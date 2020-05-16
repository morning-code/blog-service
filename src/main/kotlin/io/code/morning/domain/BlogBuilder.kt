package io.code.morning.domain

import io.code.morning.infrastructure.BlogEntity
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class BlogBuilder {

  fun build(entity: BlogEntity?): Mono<Blog> {
    return entity.let {
      Mono.just(Blog(
          id = it?.id.toString(),
          category = it?.category.orEmpty(),
          title = it?.title.orEmpty(),
          detail = it?.detail.orEmpty()
      ))
    }
  }
}