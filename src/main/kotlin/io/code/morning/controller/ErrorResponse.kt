package io.code.morning.controller

import io.code.morning.exceptions.BlogException
import org.springframework.http.ResponseEntity

class ErrorResponse(var message: String) {

  companion object {
    fun create(e: BlogException): ResponseEntity<ErrorResponse> {
      return ResponseEntity<ErrorResponse>(ErrorResponse(e.message.orEmpty()), e.httpStatusCode);
    }
  }
}
