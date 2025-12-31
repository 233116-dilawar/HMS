@echo off
echo Compiling DB Setup Utility...
"C:\Program Files\Java\jdk-21\bin\javac.exe" -cp "target\HMS\WEB-INF\lib\*" src\java\Database\SetupDB.java

echo Running DB Setup...
"C:\Program Files\Java\jdk-21\bin\java.exe" -cp "src\java;target\HMS\WEB-INF\lib\*" Database.SetupDB

pause
