@echo off
echo Patching AdminHome.jsp...
copy /Y "c:\Users\Dell\Downloads\Hospital-Management-System-main\AdminHome.jsp" "C:\Tomcat9\webapps\HMS\AdminHome.jsp"

echo Clearing Tomcat Work Cache...
rmdir /s /q "C:\Tomcat9\work\Catalina\localhost\HMS"

echo Done! Restarting Tomcat...
taskkill /F /IM java.exe
timeout /t 3
start "" "c:\Users\Dell\Downloads\Hospital-Management-System-main\start_tomcat.bat"
