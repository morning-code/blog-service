package io.code.morning.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableDynamoDBRepositories(basePackages = ["io.code.morning.infrastructure.dynamodb"])
class DynamoDbConfig {

  @Value("\${amazon.dynamodb.endpoint}")
  private val endPoint: String? = null

  @Value("\${amazon.credential.profile}")
  private val profile: String? = null

  @Bean
  fun amazonDynamoDB(): AmazonDynamoDB {
    val amazonDynamoDB = AmazonDynamoDBClient(amazonAWSCredentials())

    endPoint?.let { amazonDynamoDB.setEndpoint(endPoint) }

    return amazonDynamoDB
  }

  @Bean
  fun amazonAWSCredentials(): AWSCredentials =
      ProfileCredentialsProvider(profile).credentials
}