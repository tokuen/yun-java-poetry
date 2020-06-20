PID=$(ps -ef | grep eladmin-system-2.4.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
echo Application is already stopped
else
echo kill -9 $PID
kill -9 $PID
fi

cd /opt/app/yun-java-poetry

git pull

mvn clean

mvn compile

mvn package

cd target

nohup java -jar yun-java-poetry-0.0.1.jar --spring.profiles.active=prod & /opt/logs/yun-java-poetry/all.log