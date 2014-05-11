#!/bin/bash

cd $(dirname $0)/../..
TERM=dump ./gradlew test --info --stacktrace
