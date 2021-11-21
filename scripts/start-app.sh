#!/bin/bash

APP_ROOT_PATH=$ROOT_DIR
APP_PID_PATH=$ROOT_DIR/application.pid

if [ -z "$ROOT_DIR" ];then
  echo "ERROR: $0: You need to set up configuration ROOT_DIR to run sfo-automation"
  echo "ERROR: $0: export ROOT_DIR={your_project_path}"
  exit 1
fi

echo "$0: Run sfo-automation api"
exec java -Dspring.profiles.active=real -jar "$ROOT_DIR"/sfo-auto-java-1.0.0.jar > "$ROOT_DIR"/application.log 2>&1 & echo $! > "$APP_PID_PATH"

ps -f $(cat "$APP_PID_PATH")
