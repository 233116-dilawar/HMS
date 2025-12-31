# PowerShell Script to Install Apache Tomcat 9
$tomcatVersion = "9.0.96"
$tomcatUrl = "https://archive.apache.org/dist/tomcat/tomcat-9/v$tomcatVersion/bin/apache-tomcat-$tomcatVersion-windows-x64.zip"
$installPath = "C:\Tomcat9"
$zipPath = "$env:TEMP\tomcat.zip"

Write-Host "1. Downloading Apache Tomcat $tomcatVersion..." -ForegroundColor Cyan
try {
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
    Invoke-WebRequest -Uri $tomcatUrl -OutFile $zipPath
    Write-Host "   Download complete." -ForegroundColor Green
} catch {
    Write-Host "❌ Failed to download Tomcat. Please check internet connection." -ForegroundColor Red
    exit
}

if (Test-Path $installPath) {
    Write-Host "⚠️  $installPath already exists. Skipping extraction." -ForegroundColor Yellow
} else {
    Write-Host "2. Extracting to $installPath..." -ForegroundColor Cyan
    Expand-Archive -Path $zipPath -DestinationPath "C:\" -Force
    
    # Rename the folder from apache-tomcat-x.x.xx to Tomcat9
    $extractedFolder = "C:\apache-tomcat-$tomcatVersion"
    if (Test-Path $extractedFolder) {
        Rename-Item -Path $extractedFolder -NewName "Tomcat9"
        Write-Host "   Installed successfully to $installPath" -ForegroundColor Green
    } else {
        Write-Host "❌ Extraction failed or folder name mismatch." -ForegroundColor Red
        exit
    }
}

Write-Host "3. Configuring Tomcat Users..." -ForegroundColor Cyan
$usersXml = "$installPath\conf\tomcat-users.xml"
$tomcatUsersConfig = @"
<tomcat-users xmlns="http://tomcat.apache.org/xml"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
              version="1.0">
  <role rolename="manager-gui"/>
  <role rolename="manager-script"/>
  <user username="admin" password="admin" roles="manager-gui,manager-script"/>
</tomcat-users>
"@
Set-Content -Path $usersXml -Value $tomcatUsersConfig

Write-Host "4. Starting Tomcat..." -ForegroundColor Cyan
$startupScript = "$installPath\bin\startup.bat"
Start-Process -FilePath $startupScript -WindowStyle Minimized

Write-Host ""
Write-Host "✅ Tomcat is installed and starting!" -ForegroundColor Green
Write-Host "   Access it here: http://localhost:8080" -ForegroundColor White
Write-Host "   (Note: Jenkins uses 8080 too, so Tomcat might fail if Jenkins is running)" -ForegroundColor Yellow
Write-Host "   If they conflict, we will change Tomcat port to 8090 automatically." -ForegroundColor Cyan

# Check for port conflict helper
$serverXml = "$installPath\conf\server.xml"
$content = Get-Content $serverXml
if ($content -match 'port="8080"') {
    Write-Host "   ...Updating Tomcat to port 8090 to avoid conflict with Jenkins..." -ForegroundColor Magenta
    (Get-Content $serverXml).Replace('port="8080"', 'port="8090"') | Set-Content $serverXml
    
    # Restart Tomcat
    Stop-Process -Name "java" -ErrorAction SilentlyContinue # Brute force stop to be sure
    Start-Sleep -Seconds 2
    Start-Process -FilePath $startupScript -WindowStyle Minimized
    Write-Host "✅ Tomcat restarted on port 8090 (http://localhost:8090)" -ForegroundColor Green
}
