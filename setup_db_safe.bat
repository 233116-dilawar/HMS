@echo off
echo Preparing dependencies...
dir /b target\HMS\WEB-INF\lib\mysql*.jar > driver_name.txt
set /p DRIVER=<driver_name.txt
copy "target\HMS\WEB-INF\lib\%DRIVER%" "mysql_driver.jar" /Y

echo Compiling DB Setup Utility...
"C:\Program Files\Java\jdk-21\bin\javac.exe" -cp "mysql_driver.jar" src\java\Database\SetupDB.java

echo Running DB Setup...
"C:\Program Files\Java\jdk-21\bin\java.exe" -cp "src\java;mysql_driver.jar" Database.SetupDB

pause
