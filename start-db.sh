#!/usr/bin/env bash

set -ex

docker run -d -e POSTGRES_USER=gebco -e POSTGRES_PASSWORD=letmein -p 5432:5432 --name gebcoeip postgis/postgis