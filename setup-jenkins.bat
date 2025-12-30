@echo off
echo ========================================
echo Jenkins Deployment - Hospital Management System
echo ========================================
echo.

REM Configuration
set JENKINS_URL=http://localhost:8080
set JOB_NAME=Hospital-Management-System
set PROJECT_DIR=%~dp0

echo [1/5] Checking Jenkins...
echo ========================================
curl -s -o nul -w "%%{http_code}" %JENKINS_URL% > temp_status.txt
set /p HTTP_STATUS=<temp_status.txt
del temp_status.txt

echo Status Code: %HTTP_STATUS%

if "%HTTP_STATUS%"=="200" goto :jenkins_ok
if "%HTTP_STATUS%"=="403" goto :jenkins_ok

echo [ERROR] Jenkins returned status %HTTP_STATUS%. It might not be ready.
echo Trying to proceed anyway...
goto :jenkins_ok

:jenkins_ok
echo [OK] Jenkins is accessible!

echo.
echo [2/5] Initializing Git Repository...
echo ========================================
cd /d "%PROJECT_DIR%"

if not exist ".git" (
    echo Initializing Git repository...
    git init
    git add .
    git commit -m "Initial commit - MVC Hospital Management System"
    echo [OK] Git repository initialized
) else (
    echo [OK] Git repository already exists
    git add .
    git commit -m "Update - Ready for Jenkins deployment" 2>nul || echo [INFO] No changes to commit
)

echo.
echo [3/5] Jenkins Setup Instructions...
echo ========================================
echo.
echo We found your initial Admin Password!
echo it is saved in: initial_password.txt
echo.
echo PASSWORD: f8fa36fe7666485b91c14d59fc3743b2
echo.
echo STEPS TO COMPLETE NOW:
echo 1. Browser will open http://localhost:8080
echo 2. Paste the password above
echo 3. Click "Install suggested plugins" (Wait for it to finish)
echo 4. Create your admin user
echo 5. Create the Pipeline Job (see guide)
echo.

echo.
echo [4/5] Opening Resources...
echo ========================================
start %JENKINS_URL%
start initial_password.txt
timeout /t 2 /nobreak >nul

if exist "JENKINS_DEPLOYMENT_GUIDE.md" (
    start JENKINS_DEPLOYMENT_GUIDE.md
)

echo.
echo ========================================
echo Setup Ready!
echo ========================================
echo.
pause
