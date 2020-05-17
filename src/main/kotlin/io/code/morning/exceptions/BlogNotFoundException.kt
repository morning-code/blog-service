package io.code.morning.exceptions

import org.springframework.http.HttpStatus

class BlogNotFoundException(message: String? = null) : BlogException(HttpStatus.NOT_FOUND, message)