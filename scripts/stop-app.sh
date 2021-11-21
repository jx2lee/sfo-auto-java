#!/bin/bash

APP_PID_PATH=$ROOT_DIR/application.pid

if [ -z "$ROOT_DIR" ];then
  echo "ERROR: $0: You need to set up configuration ROOT_DIR to run sfo-automation"
  echo "ERROR: $0: export ROOT_DIR={your_project_path}"
  exit 1
fi

echo "$0: Searching for running application..."
if [ ! -f "$APP_PID_PATH" ]; then
  echo "$0: No application.pid file found"
  exit 1
fi

ps -f $(cat "$APP_PID_PATH")
if [ $? -ne 0 ]; then
  echo "$0: No running sfo-automation application found"
  exit 1
fi

echo "$0: Aforementioned process will get SIGTERM"
kill -15 $(cat "$APP_PID_PATH")

echo "$0: SIGTERM delivered"
echo "$0: Wait a moment for sfo-automation application to clean herself up"
rm "$APP_PID_PATH"
