package io.code.morning.exceptions

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

abstract class BlogException(val httpStatusCode: HttpStatus, message: String? = null) : RuntimeException(message)
