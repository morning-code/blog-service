//package io.code.morning.config

/*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger.web.UiConfigurationBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

  @Bean
  fun blogApi(): Docket {
    return Docket(DocumentationType.SWAGGER_2)
        .groupName("BlogService API")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.regex("/controller/**"))
        .build()
  }

  @Bean
  fun uiConfig(): UiConfiguration {
    return UiConfigurationBuilder.builder()
        .displayRequestDuration(true)
        .validatorUrl("")
        .build()
  }

  private fun apiInfo(): ApiInfo {
    return ApiInfoBuilder()
        .title("MorningCode API")
        .description("REST API of MorningCode")
        .version("1.0")
        .build()
  }
}

 */