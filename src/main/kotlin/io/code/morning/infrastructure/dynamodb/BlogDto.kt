package io.code.morning.infrastructure.dynamodb

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "blog")
data class BlogDto(
    @DynamoDBHashKey(attributeName = "id")
    var id: String? = null,

    @DynamoDBAttribute
    var category: String? = null,

    @DynamoDBAttribute
    var title: String? = null,

    @DynamoDBAttribute
    var summary: String? = null,

    @DynamoDBAttribute
    var detail: String? = null,

    @DynamoDBAttribute
    var createdDate: String? = null,

    @DynamoDBAttribute
    var updatedDate: String? = null
)


