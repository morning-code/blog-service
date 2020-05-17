#!/bin/bash

aws dynamodb create-table \
  --profile morning-code-local \
  --cli-input-json file://blog.json \
  --endpoint-url http://localhost:8000
