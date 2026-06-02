#!/bin/bash

./docker_build_config_server.sh
./docker_build_customer_service.sh
./docker_build_api-gateway.sh
./docker_build_currency_service.sh
