@echo off
SET DIRECTORY_HOME=C:\workspace

cd %DIRECTORY_HOME%\web-angular\
call mvn clean package install -P dev

cd %DIRECTORY_HOME%\discovery\
call mvn clean package install -P dev

::cd %DIRECTORY_HOME%\gateway\
::call mvn clean package install -P dev

cd %DIRECTORY_HOME%\gateway\
call mvn clean package install -P dev

cd %DIRECTORY_HOME%\api-user\
call mvn clean package install -P dev

cd %DIRECTORY_HOME%\api-user-data\
call mvn clean package install -P dev

@pause