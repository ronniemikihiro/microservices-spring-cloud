@echo off
SET DIRECTORY_HOME=C:\workspace

cd %DIRECTORY_HOME%\discovery\target
start "discovery" java -jar discovery.jar

cd %DIRECTORY_HOME%\gateway\target
start "gateway" java -jar gateway.jar

cd %DIRECTORY_HOME%\api-user\target
start "api-user" java -jar api-user.jar

cd %DIRECTORY_HOME%\api-user-data\target
start "api-user-data" java -jar api-user-data.jar

@pause