package io.code.morning.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import io.code.morning.infrastructure.BlogRepository
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = [BlogRepository::class])
class DynamoDbConfig {

  @Value("\${amazon.dynamodb.endpoint}")
  private val amazonDynamoDBEndpoint: String? = null

  @Value("\${amazon.credential.profile}")
  private val profile: String? = null

  @Bean
  fun amazonDynamoDB(): AmazonDynamoDB {
    val amazonDynamoDB = AmazonDynamoDBClient(amazonAWSCredentials())

    amazonDynamoDBEndpoint?.let { amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint) }

    return amazonDynamoDB
  }

  @Bean
  fun amazonAWSCredentials(): AWSCredentials {
    return ProfileCredentialsProvider(profile).credentials
  }
}