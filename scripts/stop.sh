#!/usr/bin/env bash

ABSPATH=$(readlink -f $0) # 현재 스크립트의 절대 경로
ABSDIR=$(dirname $ABSPATH) # 현재 스크립트가 있는 디렉토리 경로
source ${ABSDIR}/profile.sh # profile.sh 불러오기 (find_idle_port 함수 사용하기 위해)

IDLE_PORT=$(find_idle_port) # 쉬고 있는 포트 확인 (8081 or 8082)

echo "> $IDLE_PORT 에서 구동 중인 애플리케이션 pid 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID} # 프로세스 종료 (graceful shutdown)
  sleep 5 # 5초 대기
fi
