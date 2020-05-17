#!/bin/bash

aws dynamodb put-item \
    --profile morning-code-local \
    --table-name blog \
    --item '{"id":{"S":"1"}, "title":{"S":"Test Blog"}, "category":{"S":"test"}, "detail":{"S":"test detail"}}' \
    --endpoint-url http://localhost:8000

aws dynamodb get-item \
    --profile morning-code-local \
    --table-name blog --key '{"id":{"S":"1"}}' \
    --endpoint-url http://localhost:8000
