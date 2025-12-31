# PowerShell script to download and install Portable MariaDB
$version = "10.6.16"
$url = "https://archive.mariadb.org/mariadb-$version/winx64-packages/mariadb-$version-winx64.zip"
$zipPath = "$env:TEMP\mariadb.zip"
$installPath = "C:\MariaDB"

# 1. Download
Write-Host "Downloading MariaDB (MySQL)..."
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
Invoke-WebRequest -Uri $url -OutFile $zipPath

# 2. Extract
Write-Host "Extracting to $installPath..."
Expand-Archive -Path $zipPath -DestinationPath "C:\" -Force
$extracted = "C:\mariadb-$version-winx64"
if (Test-Path $extracted) {
    Rename-Item -Path $extracted -NewName "MariaDB" -ErrorAction SilentlyContinue
}

# 3. Initialize Data
Write-Host "Initializing Database..."
Set-Location "$installPath\bin"
.\mysql_install_db.exe --datadir="$installPath\data"

# 4. Start Server (Background)
Write-Host "Starting Database Server..."
Start-Process -FilePath ".\mysqld.exe" -ArgumentList "--console" -WindowStyle Minimized

Write-Host "✅ Database Installed & Started!"
Write-Host "   User: root"
Write-Host "   Pass: (empty)"
