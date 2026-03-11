#!/bin/bash
JAR_PATH=/home/ec2-user/app/step2/zip
JAR_NAME=$(ls $JAR_PATH/*.jar | tail -1)

echo ">>> 실행할 JAR: $JAR_NAME"

nohup java -jar $JAR_NAME > /home/ec2-user/app/step2/zip/nohup.out 2>&1 &