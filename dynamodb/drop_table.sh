#!/bin/bash

aws dynamodb delete-table \
  --profile morning-code-local \
  --table-name blog \
  --endpoint-url http://localhost:8000
