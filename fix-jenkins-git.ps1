# PowerShell Script to Enable Local Git in Jenkins
$jenkinsHome = "C:\Program Files\Jenkins"
$xmlFile = "$jenkinsHome\jenkins.xml"
$backupFile = "$jenkinsHome\jenkins.xml.bak"

Write-Host "1. Stopping Jenkins Service..." -ForegroundColor Yellow
Stop-Service -Name "jenkins" -Force

if (Test-Path $xmlFile) {
    Write-Host "2. Backing up configuration..." -ForegroundColor Yellow
    Copy-Item $xmlFile $backupFile -Force
    
    Write-Host "3. Updating configuration to allow local Git..." -ForegroundColor Yellow
    
    # Read the file
    [xml]$xml = Get-Content $xmlFile
    
    # Check if argument already exists
    $argString = $xml.service.arguments
    if ($argString -notlike "*ALLOW_LOCAL_CHECKOUT*") {
        # Add the argument to the existing arguments string
        # We insert it before -jar to be safe, or append to java args
        $newArgs = $argString.Replace(" -jar", " -Dhudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT=true -jar")
        $xml.service.arguments = $newArgs
        
        # Save back
        $xml.Save($xmlFile)
        Write-Host "✅ Configuration updated successfully." -ForegroundColor Green
    } else {
        Write-Host "Configuration already present." -ForegroundColor Cyan
    }
} else {
    Write-Host "❌ Could not find jenkins.xml at $jenkinsHome" -ForegroundColor Red
    Write-Host "Please verify your Jenkins installation path."
}

Write-Host "4. Restarting Jenkins Service..." -ForegroundColor Yellow
Start-Service -Name "jenkins"

Write-Host "5. Done! Please wait 30 seconds for Jenkins to warm up." -ForegroundColor Green
Write-Host "   Then click 'Build Now' again in your browser." -ForegroundColor White
