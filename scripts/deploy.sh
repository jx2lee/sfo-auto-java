#!/bin/bash

REPOSITORY=/app/sfo-alert
PROJECT_NAME=sfo

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(sudo pgrep -fl $PROJECT_NAME | grep java | awk '{print $1}')

echo ">> 현재 구동중인 어플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

cd $REPOSITORY
$REPOSITORY/gradlew build
JAR_NAME=$(ls -tr $REPOSITORY/build/libs/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar -Dspring.config.location=classpath:/application.properties $JAR_NAME 2>&1 & tail -f nohup.out
