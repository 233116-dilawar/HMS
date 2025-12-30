@echo off
color 0A
echo ========================================
echo Hospital Management System - MVC Launch
echo ========================================
echo.

REM Set project directory
set PROJECT_DIR=%~dp0

echo [1/4] Checking MySQL Server...
echo ========================================
echo.

REM Check if MySQL service exists and start it
sc query MySQL >nul 2>&1
if %errorlevel% equ 0 (
    echo [OK] MySQL service found!
    echo Starting MySQL service...
    net start MySQL 2>nul
    if %errorlevel% equ 0 (
        echo [OK] MySQL is now running!
    ) else (
        echo [INFO] MySQL is already running or started successfully.
    )
) else (
    echo [WARNING] MySQL service not found!
    echo.
    echo Checking for XAMPP MySQL...
    if exist "C:\xampp\mysql\bin\mysqld.exe" (
        echo [OK] XAMPP MySQL found!
        echo Please start MySQL from XAMPP Control Panel manually.
        echo Opening XAMPP Control Panel...
        start "" "C:\xampp\xampp-control.exe" 2>nul
    ) else (
        echo [ERROR] MySQL not found!
        echo Please install MySQL or XAMPP and try again.
        pause
        exit /b 1
    )
)

echo.
echo [2/4] Database Setup Check
echo ========================================
echo.
echo Please ensure:
echo  [x] MySQL is running
echo  [x] Database 'hospital' exists
echo  [x] Database is imported from hospital.sql
echo.
echo To setup database:
echo  1. Open phpMyAdmin: http://localhost/phpmyadmin
echo  2. Create database: hospital
echo  3. Import file: hospital.sql
echo.

echo [3/4] Project Information
echo ========================================
echo.
echo Project Location: %PROJECT_DIR%
echo Architecture: MVC with DAO Pattern
echo.
echo Login Credentials:
echo  Admin: admin / admin
echo  User:  123 / 123
echo.

echo [4/4] Launching NetBeans IDE...
echo ========================================
echo.

REM Try to find and launch NetBeans
set NETBEANS_FOUND=0

REM Common NetBeans installation paths
if exist "C:\Program Files\NetBeans-*\bin\netbeans64.exe" (
    for /d %%i in ("C:\Program Files\NetBeans-*") do (
        echo [OK] Found NetBeans at: %%i
        start "" "%%i\bin\netbeans64.exe" --open "%PROJECT_DIR%"
        set NETBEANS_FOUND=1
        goto :netbeans_launched
    )
)

if exist "C:\Program Files (x86)\NetBeans-*\bin\netbeans64.exe" (
    for /d %%i in ("C:\Program Files (x86)\NetBeans-*") do (
        echo [OK] Found NetBeans at: %%i
        start "" "%%i\bin\netbeans64.exe" --open "%PROJECT_DIR%"
        set NETBEANS_FOUND=1
        goto :netbeans_launched
    )
)

if exist "%ProgramFiles%\NetBeans\*\bin\netbeans64.exe" (
    for /d %%i in ("%ProgramFiles%\NetBeans\*") do (
        echo [OK] Found NetBeans at: %%i
        start "" "%%i\bin\netbeans64.exe" --open "%PROJECT_DIR%"
        set NETBEANS_FOUND=1
        goto :netbeans_launched
    )
)

:netbeans_launched
if %NETBEANS_FOUND%==0 (
    echo [WARNING] NetBeans IDE not found automatically.
    echo.
    echo Please open NetBeans manually and:
    echo  1. File -^> Open Project
    echo  2. Select: %PROJECT_DIR%
    echo  3. Right-click project -^> Clean and Build
    echo  4. Right-click project -^> Run
    echo.
) else (
    echo.
    echo [OK] NetBeans IDE launched!
    echo.
    echo Next steps in NetBeans:
    echo  1. Wait for project to load
    echo  2. Right-click project -^> Clean and Build
    echo  3. Right-click project -^> Run
    echo  4. Browser will open automatically
    echo.
)

echo.
echo ========================================
echo Opening Documentation...
echo ========================================
if exist "%PROJECT_DIR%MVC_ARCHITECTURE.md" (
    start "" "%PROJECT_DIR%MVC_ARCHITECTURE.md"
)
if exist "%PROJECT_DIR%SETUP_AND_RUN_GUIDE.md" (
    start "" "%PROJECT_DIR%SETUP_AND_RUN_GUIDE.md"
)

echo.
echo ========================================
echo All Done! Good Luck! 🎉
echo ========================================
echo.
pause
