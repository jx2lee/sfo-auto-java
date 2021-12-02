#!/bin/bash

# VARIABLES
APP_PID_PATH=$ROOT_DIR/application.pid
APP_LOG_PATH=$ROOT_DIR/application.log
APP_JAR="sfo-auto-java"
VERSION="1.0.0"
PROFILE=$1

if [ -z "$ROOT_DIR" ];then
  echo "ERROR: $0: You need to set up configuration ROOT_DIR to run sfo-automation."
  echo "ERROR: $0: export ROOT_DIR={your_project_path}"
  exit 1
fi

if [ $# -ne 1 ]; then
 echo "ERROR: $0: You need to set profiles."
 echo "ERROR: $0: sh build.sh {real/dev}"
 exit 1
fi

# BUILD & EXECUTE
echo "$0: Build Project"
"$ROOT_DIR"/gradlew clean build

echo "$0: move Jar in ROOT_DIR"
mv "$ROOT_DIR"/build/libs/"$APP_JAR"-"$VERSION".jar "$ROOT_DIR"

echo "$0: Run sfo-automation api"
exec java -Dspring.profiles.active=$1 -jar "$ROOT_DIR"/"$APP_JAR"-"$VERSION".jar > "$APP_LOG_PATH" 2>&1 & echo $! > "$APP_PID_PATH"

ps -f $(cat "$APP_PID_PATH")
