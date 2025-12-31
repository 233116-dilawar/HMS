# PowerShell script to download driver and setup DB
$url = "https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar"
$output = "mysql-connector.jar"

Write-Host "Downloading MySQL Driver..."
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
Invoke-WebRequest -Uri $url -OutFile $output

Write-Host "Compiling SetupDB..."
& "C:\Program Files\Java\jdk-21\bin\javac.exe" -cp "mysql-connector.jar" src/java/Database/SetupDB.java

Write-Host "Running SetupDB..."
& "C:\Program Files\Java\jdk-21\bin\java.exe" -cp "src/java;mysql-connector.jar" Database.SetupDB
