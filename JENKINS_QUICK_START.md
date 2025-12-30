# 🚀 Quick Jenkins Setup & Deployment Guide

## ✅ Current Status

- ✅ **Jenkins Installed** - Running on http://localhost:8080
- ✅ **Java 1.8.0_221** - Installed and configured
- ⏳ **Jenkins Setup** - Needs initial configuration
- ✅ **Project Files Ready** - All deployment files created

---

## 🔓 Step 1: Unlock Jenkins (FIRST TIME ONLY)

### Get Initial Admin Password:

**Option A: Using File Explorer**
1. Open File Explorer
2. Navigate to: `C:\ProgramData\Jenkins\.jenkins\secrets\`
3. Open file: `initialAdminPassword`
4. Copy the password

**Option B: Using Command**
```powershell
Get-Content "C:\ProgramData\Jenkins\.jenkins\secrets\initialAdminPassword"
```

**Option C: Check User Directory**
```powershell
Get-Content "$env:USERPROFILE\.jenkins\secrets\initialAdminPassword"
```

### Unlock Jenkins:
1. Open browser: http://localhost:8080
2. Paste the admin password
3. Click "Continue"

---

## 📦 Step 2: Install Plugins

1. **Select "Install suggested plugins"**
   - This will install all necessary plugins
   - Wait for installation to complete (2-5 minutes)

2. **Additional Required Plugins:**
   - Pipeline
   - Git
   - Email Extension
   - All should be included in suggested plugins

---

## 👤 Step 3: Create Admin User

1. Fill in the form:
   - Username: `admin`
   - Password: `admin` (or your choice)
   - Full name: `Admin`
   - Email: `admin@hospital.com`

2. Click "Save and Continue"

3. **Instance Configuration:**
   - Jenkins URL: `http://localhost:8080/`
   - Click "Save and Finish"

4. Click "Start using Jenkins"

---

## 🎯 Step 4: Create Jenkins Job for Hospital Management System

### Method 1: Manual Creation (Recommended)

1. **On Jenkins Dashboard:**
   - Click "New Item"
   - Enter name: `Hospital-Management-System`
   - Select "Pipeline"
   - Click "OK"

2. **Configure the Job:**

   **General:**
   - Description: `Hospital Management System - MVC Architecture`
   - ✅ Check "Discard old builds"
   - Days to keep: 30
   - Max # of builds: 10

   **Build Triggers:**
   - ✅ Poll SCM: `H/5 * * * *` (checks every 5 minutes)

   **Pipeline:**
   - Definition: `Pipeline script from SCM`
   - SCM: `Git`
   - Repository URL: `file:///C:/Users/Dell/Downloads/Hospital-Management-System-main`
   - Branch Specifier: `*/master`
   - Script Path: `Jenkinsfile`

3. **Click "Save"**

### Method 2: Import Job Configuration

1. Copy `jenkins-job-config.xml` to Jenkins jobs folder:
   ```powershell
   Copy-Item jenkins-job-config.xml "C:\ProgramData\Jenkins\.jenkins\jobs\Hospital-Management-System\config.xml"
   ```

2. Restart Jenkins or reload configuration

---

## 🔧 Step 5: Configure Global Tools

1. **Go to:** Manage Jenkins → Global Tool Configuration

2. **Add JDK:**
   - Click "Add JDK"
   - Name: `JDK8`
   - Uncheck "Install automatically"
   - JAVA_HOME: `C:\Program Files\Java\jdk1.8.0_221`

3. **Add Ant (if installed):**
   - Click "Add Ant"
   - Name: `Ant`
   - ANT_HOME: `C:\apache-ant-x.x.x`

4. **Click "Save"**

---

## 🚀 Step 6: Deploy the Application

### Initialize Git Repository:

```powershell
cd C:\Users\Dell\Downloads\Hospital-Management-System-main
git init
git add .
git commit -m "Initial commit - Hospital Management System"
```

### Trigger Build:

1. **Go to Jenkins Dashboard**
2. **Click on "Hospital-Management-System"**
3. **Click "Build Now"**

4. **Watch the Build:**
   - Click on build number (e.g., #1)
   - Click "Console Output"
   - Watch the pipeline stages execute

### Pipeline Stages:
```
✅ Checkout          - Get code from repository
✅ Environment Check - Verify Java, Ant
⚠️  Database Setup   - Check MySQL (may warn if not installed)
✅ Build             - Compile Java code
✅ Test              - Run tests
✅ Code Quality      - Analyze code
✅ Package           - Create WAR file
✅ Deploy to Tomcat  - Deploy application
✅ Health Check      - Verify deployment
✅ Smoke Test        - Test pages
```

---

## 📋 Quick Commands

### Start Jenkins (if not running):
```powershell
# If installed as service
net start jenkins

# If using WAR file
java -jar jenkins.war --httpPort=8080
```

### Check Jenkins Status:
```powershell
Get-Service jenkins
```

### Access Jenkins:
```
http://localhost:8080
```

### View Logs:
```
C:\ProgramData\Jenkins\.jenkins\logs\
```

---

## 🎯 After Successful Build

### Application URLs:
- **Application:** http://localhost:8080/HMS/
- **Admin Login:** http://localhost:8080/HMS/adminLogin.jsp
- **User Login:** http://localhost:8080/HMS/index.jsp

### Login Credentials:
- **Admin:** `admin` / `admin`
- **User:** `123` / `123`

---

## ⚠️ Important Notes

### MySQL Requirement:
The application requires MySQL to function. If MySQL is not installed:

1. **Install MySQL:**
   - Download: https://dev.mysql.com/downloads/installer/
   - OR install XAMPP: https://www.apachefriends.org/

2. **Setup Database:**
   ```sql
   CREATE DATABASE hospital;
   USE hospital;
   SOURCE C:\Users\Dell\Downloads\Hospital-Management-System-main\hospital.sql;
   ```

### Tomcat Requirement:
Jenkins pipeline will deploy to Tomcat. Ensure:
- Tomcat 9.0 is installed
- Path in Jenkinsfile matches your Tomcat installation
- Default: `C:\Program Files\Apache Software Foundation\Tomcat 9.0`

---

## 🔍 Troubleshooting

### Issue: Can't find initialAdminPassword
**Solution:**
```powershell
# Search for the file
Get-ChildItem -Path C:\ -Filter initialAdminPassword -Recurse -ErrorAction SilentlyContinue
```

### Issue: Build fails - Java not found
**Solution:**
- Configure JDK in Global Tool Configuration
- Ensure JAVA_HOME is set

### Issue: Build fails - MySQL connection
**Solution:**
- Install and start MySQL
- Create database: `hospital`
- Import: `hospital.sql`

### Issue: Deployment fails - Tomcat not found
**Solution:**
- Install Apache Tomcat 9.0
- Update TOMCAT_HOME in Jenkinsfile
- Ensure Tomcat has write permissions

---

## 📞 Quick Reference

### Files Created:
- ✅ `Jenkinsfile` - Pipeline configuration
- ✅ `pom.xml` - Maven build file
- ✅ `deploy.bat` - Manual deployment script
- ✅ `jenkins-job-config.xml` - Job configuration
- ✅ `setup-jenkins.bat` - Quick setup script

### Key Directories:
- **Jenkins Home:** `C:\ProgramData\Jenkins\.jenkins\`
- **Project:** `C:\Users\Dell\Downloads\Hospital-Management-System-main\`
- **Tomcat:** `C:\Program Files\Apache Software Foundation\Tomcat 9.0\`

---

## ✨ Next Steps

1. ✅ Unlock Jenkins with admin password
2. ✅ Install suggested plugins
3. ✅ Create admin user
4. ✅ Configure global tools (JDK)
5. ✅ Create pipeline job
6. ✅ Initialize Git repository
7. ✅ Build the project
8. ✅ Access deployed application

---

**Ready to deploy! Follow the steps above to get your Hospital Management System running on Jenkins! 🚀**

**Created:** 2025-12-30  
**Status:** Ready for Jenkins Setup
