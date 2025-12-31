@echo off
echo Configuring Java Environment...
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
set "JRE_HOME=C:\Program Files\Java\jdk-21"

echo Starting Tomcat...
cd /d "C:\Tomcat9\bin"
call startup.bat

echo.
echo ===================================================
echo Tomcat Launched! 
echo Wait 15-30 seconds, then check: http://localhost:8090/HMS/
echo ===================================================
pause
