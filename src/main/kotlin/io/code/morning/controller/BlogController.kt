package io.code.morning.controller

import io.code.morning.domain.Blog
import io.code.morning.usecase.BlogUsecase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.HttpStatus
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
    ApiResponse(responseCode = "401", description = "Not authenticated"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = ["list"])
  suspend fun list(): List<BlogResponse> = blogUsecase.findList().awaitSingle()

  @Operation(summary = "get a blog specified by id")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Not authenticated"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  suspend fun find(@PathVariable("id") id: String): BlogResponse = blogUsecase.findById(id).awaitSingle()

  @Operation(summary = "create a blog")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Not authenticated"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = ["/"])
  suspend fun create(@RequestBody blog: Blog): Blog = blogUsecase.create(blog).awaitSingle()

  @Operation(summary = "update a blog")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Not authenticated"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @PutMapping(value = ["/{id}"])
  suspend fun update(@PathVariable("id") id: String, @RequestBody blog: Blog) = blogUsecase.update(id, blog)

  @Operation(summary = "delete a blog")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Not authenticated"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = ["/{id}"])
  suspend fun delete(@PathVariable("id") id: String) = blogUsecase.delete(id)
}