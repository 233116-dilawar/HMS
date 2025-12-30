@echo off
echo ========================================
echo Hospital Management System - Deployment
echo ========================================
echo.

REM Configuration
set TOMCAT_HOME=C:\Program Files\Apache Software Foundation\Tomcat 9.0
set APP_NAME=HMS
set PROJECT_DIR=%~dp0

echo [1/6] Checking Prerequisites...
echo ========================================

REM Check Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java not found!
    pause
    exit /b 1
)
echo [OK] Java installed

REM Check Ant
ant -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [WARNING] Ant not found, trying Maven...
    mvn -version >nul 2>&1
    if %errorlevel% neq 0 (
        echo [ERROR] Neither Ant nor Maven found!
        pause
        exit /b 1
    )
    set BUILD_TOOL=maven
) else (
    set BUILD_TOOL=ant
)
echo [OK] Build tool: %BUILD_TOOL%

echo.
echo [2/6] Checking MySQL...
echo ========================================
sc query MySQL80 >nul 2>&1
if %errorlevel% equ 0 (
    echo [OK] MySQL service found
    net start MySQL80 2>nul
) else (
    echo [WARNING] MySQL not found - application may not work properly
)

echo.
echo [3/6] Building Application...
echo ========================================
cd /d "%PROJECT_DIR%"

if "%BUILD_TOOL%"=="ant" (
    echo Building with Ant...
    call ant clean
    call ant compile
    call ant dist
) else (
    echo Building with Maven...
    call mvn clean package
)

if %errorlevel% neq 0 (
    echo [ERROR] Build failed!
    pause
    exit /b 1
)
echo [OK] Build successful

echo.
echo [4/6] Stopping Tomcat...
echo ========================================
taskkill /F /IM tomcat9.exe /T 2>nul
if %errorlevel% equ 0 (
    echo [OK] Tomcat stopped
    timeout /t 5 /nobreak >nul
) else (
    echo [INFO] Tomcat was not running
)

echo.
echo [5/6] Deploying Application...
echo ========================================

REM Remove old deployment
if exist "%TOMCAT_HOME%\webapps\%APP_NAME%" (
    echo Removing old deployment...
    rmdir /s /q "%TOMCAT_HOME%\webapps\%APP_NAME%"
)
if exist "%TOMCAT_HOME%\webapps\%APP_NAME%.war" (
    del /f "%TOMCAT_HOME%\webapps\%APP_NAME%.war"
)

REM Copy WAR file
if "%BUILD_TOOL%"=="ant" (
    if exist "dist\*.war" (
        echo Copying WAR file from dist...
        copy /Y "dist\*.war" "%TOMCAT_HOME%\webapps\%APP_NAME%.war"
    ) else (
        echo [ERROR] WAR file not found in dist folder!
        pause
        exit /b 1
    )
) else (
    if exist "target\HMS.war" (
        echo Copying WAR file from target...
        copy /Y "target\HMS.war" "%TOMCAT_HOME%\webapps\%APP_NAME%.war"
    ) else (
        echo [ERROR] WAR file not found in target folder!
        pause
        exit /b 1
    )
)

if %errorlevel% neq 0 (
    echo [ERROR] Deployment failed!
    pause
    exit /b 1
)
echo [OK] WAR file deployed

echo.
echo [6/6] Starting Tomcat...
echo ========================================
start "" "%TOMCAT_HOME%\bin\startup.bat"
echo [OK] Tomcat starting...

echo.
echo Waiting for application to deploy (30 seconds)...
timeout /t 30 /nobreak

echo.
echo ========================================
echo Deployment Complete!
echo ========================================
echo.
echo Application URL: http://localhost:8080/%APP_NAME%/
echo.
echo Login Credentials:
echo   Admin: admin / admin
echo   User:  123 / 123
echo.
echo Opening browser...
start http://localhost:8080/%APP_NAME%/

pause
