#!/usr/bin/env bash

ABSPATH=$(readlink -f $0) # 현재 스크립트의 절대 경로
ABSDIR=$(dirname $ABSPATH) # 현재 스크립트가 있는 디렉토리 경로
source ${ABSDIR}/profile.sh # profile.sh 불러오기 (find_idle_port 함수 사용하기 위해)

function switch_proxy() {
    IDLE_PORT=$(find_idle_port) # 쉬고 있는 포트 확인

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
# Nginx 설정 파일을 새 포트로 덮어쓰기
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" |
    sudo tee /etc/nginx/conf.d/service-url.inc

    echo "> 엔진엑스 Reload"
    sudo service nginx reload # nginx 재시작 아닌 reload (무중단)
}

# restart -> Nginx 완전히 껏다 켬 (순간 중단 발생)
# reload -> 설정만 다시 읽음 (서비스 중단 없음)