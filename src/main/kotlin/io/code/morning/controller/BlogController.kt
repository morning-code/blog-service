package io.code.morning.controller

import io.code.morning.domain.BlogEntity
import io.code.morning.infrastructure.BlogId
import io.code.morning.usecase.BlogUsecase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1/blog")
class BlogController(
    private val blogUsecase: BlogUsecase
) {

  @Operation(summary = "get blog list")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = ["list"])
  suspend fun list(
      @RequestParam("page") page: Int,
      @RequestParam("size") size: Int
  ): List<BlogResponse> =
      blogUsecase.findList(PageRequest.of(page, size)).awaitSingle()

  @Operation(summary = "get a blog specified by id")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  suspend fun find(@PathVariable("id") id: String): BlogResponse =
      blogUsecase.findById(BlogId(id))
          .awaitSingle()

  @Operation(summary = "create a blog")
  @ApiResponses(value = [
    ApiResponse(responseCode = "201", description = "Created"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = ["/{id}"])
  suspend fun create(@PathVariable("id") id: String, @RequestBody requestBody: BlogAddRequest): BlogResponse =
      requestBody.let {
        blogUsecase.create(BlogEntity(
            id = BlogId(id),
            category = it.category,
            title = it.title,
            detail = it.detail
        ))
      }.awaitSingle()

  @Operation(summary = "update a blog")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = ["/{id}"])
  suspend fun update(@PathVariable("id") id: String, @RequestBody requestBody: BlogUpdateRequest) =
      requestBody.let {
        blogUsecase.update(
            BlogEntity(
                id = BlogId(id),
                category = it.category,
                title = it.title,
                detail = it.detail
            ))
      }.awaitSingle()

  @Operation(summary = "delete a blog")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = ["/{id}"])
  suspend fun delete(@PathVariable("id") id: String): ResponseEntity<Unit> =
      blogUsecase.delete(BlogId(id)).let { ResponseEntity.ok().build() }


  @Operation(summary = "ping")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.CREATED)
  @GetMapping(value = ["/ping"])
  suspend fun ping(): String = Mono.just("pong").awaitSingle()
}