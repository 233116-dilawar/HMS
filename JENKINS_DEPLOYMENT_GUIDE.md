# 🚀 Jenkins Deployment Guide - Hospital Management System

## 📋 Table of Contents
1. [Prerequisites](#prerequisites)
2. [Jenkins Setup](#jenkins-setup)
3. [Project Configuration](#project-configuration)
4. [Pipeline Setup](#pipeline-setup)
5. [Deployment Process](#deployment-process)
6. [Troubleshooting](#troubleshooting)

---

## 📦 Prerequisites

### Required Software:
- ✅ **Jenkins** (Latest LTS version)
- ✅ **Java JDK 8+**
- ✅ **Apache Ant** or **Maven**
- ✅ **Apache Tomcat 9.0**
- ✅ **MySQL Server 8.0**
- ✅ **Git** (for version control)

### Jenkins Plugins Required:
1. **Pipeline Plugin**
2. **Git Plugin**
3. **Email Extension Plugin**
4. **Build Timeout Plugin**
5. **Timestamper Plugin**

---

## 🔧 Jenkins Setup

### Step 1: Install Jenkins

1. **Download Jenkins:**
   - Visit: https://www.jenkins.io/download/
   - Download Windows installer or WAR file

2. **Install Jenkins:**
   ```powershell
   # If using installer, run the .msi file
   # If using WAR file:
   java -jar jenkins.war --httpPort=8081
   ```

3. **Access Jenkins:**
   - Open browser: http://localhost:8081
   - Get initial admin password from: `C:\Users\<YourUser>\.jenkins\secrets\initialAdminPassword`
   - Install suggested plugins

### Step 2: Configure Global Tools

1. **Configure JDK:**
   - Manage Jenkins → Global Tool Configuration
   - Add JDK:
     - Name: `JDK8`
     - JAVA_HOME: `C:\Program Files\Java\jdk1.8.0_xxx`

2. **Configure Ant:**
   - Add Ant:
     - Name: `Ant`
     - ANT_HOME: `C:\apache-ant-x.x.x`

3. **Configure Maven (Optional):**
   - Add Maven:
     - Name: `Maven3`
     - Install automatically or specify MAVEN_HOME

### Step 3: Install Required Plugins

Navigate to: **Manage Jenkins → Manage Plugins → Available**

Install:
- Pipeline
- Git
- Email Extension
- Build Timeout
- Timestamper

---

## 🎯 Project Configuration

### Step 1: Prepare Your Repository

1. **Initialize Git Repository:**
   ```powershell
   cd C:\Users\Dell\Downloads\Hospital-Management-System-main
   git init
   git add .
   git commit -m "Initial commit - MVC Hospital Management System"
   ```

2. **Push to Remote (Optional):**
   ```powershell
   git remote add origin https://github.com/yourusername/hospital-management-system.git
   git push -u origin master
   ```

### Step 2: Configure Database

1. **Ensure MySQL is Running:**
   ```powershell
   net start MySQL80
   ```

2. **Create Database:**
   ```sql
   CREATE DATABASE hospital;
   USE hospital;
   SOURCE C:\Users\Dell\Downloads\Hospital-Management-System-main\hospital.sql;
   ```

### Step 3: Configure Tomcat

1. **Edit tomcat-users.xml:**
   Location: `C:\Program Files\Apache Software Foundation\Tomcat 9.0\conf\tomcat-users.xml`
   
   Add:
   ```xml
   <role rolename="manager-gui"/>
   <role rolename="manager-script"/>
   <user username="admin" password="admin" roles="manager-gui,manager-script"/>
   ```

2. **Restart Tomcat**

---

## 🔄 Pipeline Setup

### Step 1: Create New Pipeline Job

1. **In Jenkins Dashboard:**
   - Click "New Item"
   - Enter name: `Hospital-Management-System`
   - Select: "Pipeline"
   - Click OK

### Step 2: Configure Pipeline

1. **General Settings:**
   - ✅ Check "Discard old builds"
   - Keep last 10 builds

2. **Build Triggers:**
   - ✅ Poll SCM: `H/5 * * * *` (every 5 minutes)
   - OR
   - ✅ GitHub hook trigger (if using GitHub)

3. **Pipeline Configuration:**
   
   **Option A: Pipeline from SCM**
   - Definition: Pipeline script from SCM
   - SCM: Git
   - Repository URL: Your Git URL
   - Branch: `*/master`
   - Script Path: `Jenkinsfile`

   **Option B: Pipeline Script**
   - Definition: Pipeline script
   - Copy content from `Jenkinsfile` into script box

### Step 3: Configure Environment Variables

In Pipeline configuration, add:

```groovy
environment {
    TOMCAT_HOME = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0'
    MYSQL_HOST = 'localhost'
    MYSQL_PORT = '3306'
    MYSQL_DB = 'hospital'
    MYSQL_USER = 'root'
    MYSQL_PASSWORD = ''
}
```

---

## 🚀 Deployment Process

### Automatic Deployment (Recommended)

1. **Trigger Build:**
   - Push code to repository
   - Jenkins automatically detects changes
   - Pipeline starts automatically

2. **Manual Trigger:**
   - Go to Jenkins Dashboard
   - Click on "Hospital-Management-System"
   - Click "Build Now"

### Pipeline Stages:

```
1. Checkout          → Get code from repository
2. Environment Check → Verify Java, Ant/Maven
3. Database Setup    → Check MySQL connection
4. Build             → Compile Java code
5. Test              → Run unit tests
6. Code Quality      → Analyze code
7. Package           → Create WAR file
8. Deploy to Tomcat  → Deploy application
9. Health Check      → Verify deployment
10. Smoke Test       → Test critical pages
```

### Manual Deployment Script

If you want to deploy without Jenkins:

```powershell
cd C:\Users\Dell\Downloads\Hospital-Management-System-main
.\deploy.bat
```

---

## 📊 Monitoring Deployment

### View Build Progress:

1. **Console Output:**
   - Click on build number
   - Click "Console Output"
   - Watch real-time logs

2. **Pipeline Visualization:**
   - View stage-by-stage progress
   - See which stage is running
   - Identify failures quickly

### Post-Deployment Verification:

1. **Check Application:**
   - URL: http://localhost:8080/HMS/
   - Login: admin / admin

2. **Check Logs:**
   - Tomcat logs: `%TOMCAT_HOME%\logs\catalina.out`
   - Application logs: Check console

---

## 🔍 Troubleshooting

### Common Issues:

#### 1. Build Fails - Java Not Found
```
Solution:
- Configure JDK in Global Tool Configuration
- Ensure JAVA_HOME is set correctly
```

#### 2. MySQL Connection Failed
```
Solution:
- Start MySQL: net start MySQL80
- Verify database exists: hospital
- Check credentials in DatabaseConnection.java
```

#### 3. Tomcat Deployment Failed
```
Solution:
- Check Tomcat is installed
- Verify TOMCAT_HOME path in Jenkinsfile
- Ensure Tomcat has write permissions
```

#### 4. WAR File Not Found
```
Solution:
- Check build completed successfully
- Verify dist/ or target/ folder exists
- Check build.xml or pom.xml configuration
```

#### 5. Application Not Accessible
```
Solution:
- Wait 30 seconds for deployment
- Check Tomcat is running
- Verify URL: http://localhost:8080/HMS/
- Check firewall settings
```

---

## 📧 Email Notifications

### Configure Email:

1. **Manage Jenkins → Configure System**
2. **Extended E-mail Notification:**
   - SMTP server: smtp.gmail.com
   - SMTP port: 587
   - Use TLS: ✅
   - Credentials: Add your email

3. **Update Jenkinsfile:**
   ```groovy
   emailext (
       to: 'your-email@example.com',
       subject: "Build ${env.BUILD_NUMBER}",
       body: "Build completed"
   )
   ```

---

## 🎯 Best Practices

### 1. Version Control
- ✅ Commit regularly
- ✅ Use meaningful commit messages
- ✅ Tag releases: `git tag v2.0`

### 2. Database Management
- ✅ Keep database schema in version control
- ✅ Use migration scripts
- ✅ Backup database before deployment

### 3. Testing
- ✅ Add unit tests
- ✅ Run tests before deployment
- ✅ Implement smoke tests

### 4. Security
- ✅ Don't commit passwords
- ✅ Use Jenkins credentials
- ✅ Secure Tomcat manager

### 5. Monitoring
- ✅ Check build logs
- ✅ Monitor application logs
- ✅ Set up alerts

---

## 📝 Quick Reference

### Jenkins URLs:
- **Jenkins Dashboard:** http://localhost:8081
- **Build History:** http://localhost:8081/job/Hospital-Management-System/
- **Console Output:** http://localhost:8081/job/Hospital-Management-System/lastBuild/console

### Application URLs:
- **Application:** http://localhost:8080/HMS/
- **Admin Login:** http://localhost:8080/HMS/adminLogin.jsp
- **User Login:** http://localhost:8080/HMS/index.jsp

### Commands:
```powershell
# Start MySQL
net start MySQL80

# Start Tomcat
cd "C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin"
startup.bat

# Build manually
ant clean compile dist

# Deploy manually
.\deploy.bat
```

---

## 🎓 Next Steps

1. ✅ Set up automated testing
2. ✅ Configure SonarQube for code quality
3. ✅ Implement Docker containers
4. ✅ Set up staging environment
5. ✅ Configure backup automation

---

## 📞 Support

**Documentation:**
- `MVC_ARCHITECTURE.md` - Architecture details
- `QUICK_START.md` - Quick start guide
- `SETUP_AND_RUN_GUIDE.md` - Setup instructions

**Logs:**
- Jenkins: `C:\Users\<User>\.jenkins\jobs\Hospital-Management-System\builds\`
- Tomcat: `%TOMCAT_HOME%\logs\`

---

**Created:** 2025-12-30  
**Version:** 2.0  
**Status:** Production Ready ✅
