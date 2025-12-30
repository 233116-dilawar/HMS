# ⚠️ IMPORTANT: MySQL Setup Required

## 🚨 Current Status

**MySQL is NOT installed or running on your system.**

Your system has **SQL Server** installed, but the Hospital Management System requires **MySQL**.

---

## 📥 Install MySQL - Quick Guide

### **Option 1: Install MySQL Server (Recommended)**

1. **Download MySQL Installer**:
   - Go to: https://dev.mysql.com/downloads/installer/
   - Download: `mysql-installer-community-8.0.x.msi` (Windows)

2. **Install MySQL**:
   - Run the installer
   - Choose "Developer Default" or "Server only"
   - Set root password: Leave it **EMPTY** (or remember it)
   - Complete installation

3. **Start MySQL Service**:
   ```powershell
   net start MySQL80
   ```

### **Option 2: Install XAMPP (Easier for Beginners)**

1. **Download XAMPP**:
   - Go to: https://www.apachefriends.org/
   - Download XAMPP for Windows

2. **Install XAMPP**:
   - Run installer
   - Install to `C:\xampp`
   - Complete installation

3. **Start MySQL**:
   - Open XAMPP Control Panel
   - Click "Start" next to MySQL
   - Click "Start" next to Apache (for phpMyAdmin)

---

## 🗄️ Setup Database

Once MySQL is running:

### **Step 1: Access phpMyAdmin**
- Open browser: http://localhost/phpmyadmin
- Or use MySQL Workbench

### **Step 2: Create Database**
```sql
CREATE DATABASE hospital;
```

### **Step 3: Import Database**
1. Select `hospital` database
2. Click "Import" tab
3. Choose file: `hospital.sql` (in this project folder)
4. Click "Go"

---

## 🚀 After MySQL is Setup

### **NetBeans is Already Launching!**

Once NetBeans opens:

1. **Wait for project to load** (may take a minute)

2. **Configure Tomcat Server** (if needed):
   - Tools → Servers
   - Add Server → Apache Tomcat
   - Browse to Tomcat installation folder
   - Click Finish

3. **Clean and Build**:
   - Right-click project "HMS"
   - Select "Clean and Build"
   - Wait for "BUILD SUCCESSFUL"

4. **Run Project**:
   - Right-click project "HMS"
   - Select "Run"
   - Browser will open automatically

5. **Login**:
   - Admin: `admin` / `admin`
   - User: `123` / `123`

---

## 🔧 Alternative: Use SQL Server (Advanced)

If you want to use SQL Server instead of MySQL, you'll need to:

1. Update `DatabaseConnection.java`:
   ```java
   String url = "jdbc:sqlserver://localhost:1433;databaseName=hospital";
   String userName = "sa";
   String password = "your_password";
   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
   ```

2. Add SQL Server JDBC driver to `WEB-INF/lib/`

3. Convert MySQL SQL to SQL Server syntax

**Note**: This requires significant changes. MySQL is recommended.

---

## 📞 Quick Help

**MySQL Not Starting?**
```powershell
# Check MySQL service
Get-Service | Where-Object {$_.Name -like "*mysql*"}

# Start MySQL (use correct service name)
net start MySQL80
# OR
net start MySQL
```

**Database Connection Error?**
- Check MySQL is running: `Test-NetConnection localhost -Port 3306`
- Verify credentials in `DatabaseConnection.java`
- Ensure database `hospital` exists

---

## ✅ Checklist

- [ ] MySQL installed and running
- [ ] Database `hospital` created
- [ ] Database imported from `hospital.sql`
- [ ] NetBeans opened (should be launching now)
- [ ] Project loaded in NetBeans
- [ ] Clean and Build successful
- [ ] Project running
- [ ] Can access: http://localhost:8080/HMS/

---

## 🎯 Next Steps

1. **Install MySQL** (Option 1 or 2 above)
2. **Setup database** (create + import)
3. **Return to NetBeans** (already launching)
4. **Build and Run** the project

---

**Need Help?** Check `QUICK_START.md` for detailed instructions.

**Created**: 2025-12-29  
**Status**: Waiting for MySQL installation
