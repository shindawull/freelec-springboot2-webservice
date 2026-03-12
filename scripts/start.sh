#!/usr/bin/env bash
#JAR_PATH=/home/ec2-user/app/step2/zip
#JAR_NAME=$(ls $JAR_PATH/*.jar | tail -1)
#echo ">>> 실행할 JAR: $JAR_NAME"
#nohup java -jar $JAR_NAME > /home/ec2-user/app/step2/zip/nohup.out 2>&1 &

ABSPATH=$(readlink -f $0) # 현재 스크립트의 절대 경로
ABSDIR=$(dirname $ABSPATH) # 현재 스크립트가 있는 디렉토리 경로
source ${ABSDIR}/profile.sh # profile.sh 불러오기 (find_idle_port 함수 사용하기 위해)

REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=freelec-springboot2-webservice

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real.properties \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &