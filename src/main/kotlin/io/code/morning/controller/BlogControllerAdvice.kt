package io.code.morning.controller

import io.code.morning.exceptions.BlogNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class BlogControllerAdvice {
  @ExceptionHandler(BlogNotFoundException::class)
  fun getException(e: BlogNotFoundException): ResponseEntity<ErrorResponse> {
    return ErrorResponse.create(e);
  }
}