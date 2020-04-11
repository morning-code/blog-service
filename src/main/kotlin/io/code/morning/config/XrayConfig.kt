package io.code.morning.config

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class XrayConfig {
    @Bean
    fun awsxRayServletFilter() = FilterRegistrationBean<AWSXRayServletFilter>().apply {
        val registration = FilterRegistrationBean<AWSXRayServletFilter>()
        registration.filter = AWSXRayServletFilter()
        registration.order = 1
        registration.addUrlPatterns("*")
        registration.setName("XRayServletFilter")
        registration.addInitParameter("fixedName", "morning-code-blog-service")
        return registration
    }
}