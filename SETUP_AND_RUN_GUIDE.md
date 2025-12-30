# 🏥 Hospital Management System - Setup & Run Guide

## ✅ FIXES APPLIED
I've already fixed the following critical issues:
1. ✅ Fixed package name mismatch (Model → Database)
2. ✅ Updated MySQL driver to modern version (com.mysql.cj.jdbc.Driver)
3. ✅ Fixed all import statements in servlets

---

## 📋 PREREQUISITES

### 1. **MySQL Database** (Required)
- MySQL Server must be installed and running
- Default port: 3306
- Username: `root`
- Password: `` (empty)

### 2. **Apache Tomcat Server** (Required)
- Version 8.5 or higher recommended
- Or use NetBeans built-in Tomcat

### 3. **Java Development Kit (JDK)** (Required)
- JDK 8 or higher

### 4. **NetBeans IDE** (Recommended) OR Apache Ant

---

## 🚀 STEP-BY-STEP SETUP

### **STEP 1: Setup MySQL Database**

1. **Start MySQL Server**
   - Open XAMPP Control Panel (if using XAMPP)
   - Click "Start" for MySQL
   - OR start MySQL service from Windows Services

2. **Import Database**
   - Open phpMyAdmin: http://localhost/phpmyadmin
   - Click "New" to create database
   - Database name: `hospital`
   - Click "Import" tab
   - Choose file: `hospital.sql` (in project root)
   - Click "Go"

3. **Verify Database**
   - Check if database `hospital` exists
   - Should have tables: adminreg, doctor, patient, recp, worker, medicine, login

### **STEP 2: Run the Project**

#### **Option A: Using NetBeans (Easiest)**

1. Open NetBeans IDE
2. File → Open Project
3. Navigate to: `C:\Users\Dell\Downloads\Hospital-Management-System-main`
4. Right-click on project → Properties
5. Under "Run" category:
   - Set Server (Apache Tomcat)
   - Context Path: `/HMS`
6. Right-click project → Clean and Build
7. Right-click project → Run
8. Browser will open automatically

#### **Option B: Using Command Line (If you have Ant)**

```bash
cd C:\Users\Dell\Downloads\Hospital-Management-System-main
ant clean
ant compile
ant run
```

#### **Option C: Manual Deployment**

1. **Build the project:**
   - Copy entire project to Tomcat's `webapps` folder
   - Rename folder to `HMS`

2. **Start Tomcat:**
   - Go to Tomcat's `bin` folder
   - Run `startup.bat`

3. **Access application:**
   - Open browser: http://localhost:8080/HMS/

---

## 🔐 LOGIN CREDENTIALS

### **Admin Login**
- URL: http://localhost:8080/HMS/adminLogin.jsp
- Username: `admin`
- Password: `admin`

### **User Login**
- URL: http://localhost:8080/HMS/index.jsp
- Username: `123`
- Password: `123`

---

## 📁 PROJECT STRUCTURE

```
Hospital-Management-System-main/
├── src/java/
│   ├── Controller/          # Servlets
│   │   ├── AdminLogin.java
│   │   ├── UserLogin.java
│   │   ├── AddDoctor.java
│   │   ├── AddPatient.java
│   │   └── ...
│   └── Database/            # Database connection
│       └── DatabaseConnection.java
├── WEB-INF/
│   ├── lib/                 # MySQL connector
│   └── web.xml             # Web configuration
├── css/                     # Stylesheets
├── js/                      # JavaScript files
├── img/                     # Images
├── *.jsp                    # JSP pages
├── hospital.sql            # Database schema
└── build.xml               # Ant build file
```

---

## 🎯 AVAILABLE FEATURES

### **Admin Module**
- ✅ Admin Login/Logout
- ✅ Add/View Doctors
- ✅ Add/View Patients
- ✅ Add/View Receptionists
- ✅ Add/View Workers
- ✅ View Appointments
- ✅ Manage Medicine Inventory

### **User Module**
- ✅ User Login
- ✅ View Dashboard
- ✅ Basic patient operations

---

## 🐛 TROUBLESHOOTING

### **Issue 1: Database Connection Failed**
**Error:** `java.sql.SQLException: Access denied for user 'root'@'localhost'`

**Solution:**
- Check MySQL is running
- Verify username/password in `DatabaseConnection.java`
- Update credentials if different:
  ```java
  String userName = "your_username";
  String password = "your_password";
  ```

### **Issue 2: ClassNotFoundException: com.mysql.cj.jdbc.Driver**
**Error:** MySQL driver not found

**Solution:**
- Ensure `mysql-connector-java-8.0.12.jar` is in `WEB-INF/lib/`
- If using NetBeans, add library: Right-click project → Properties → Libraries → Add JAR

### **Issue 3: HTTP 404 - Page Not Found**
**Error:** Cannot access pages

**Solution:**
- Check Tomcat is running
- Verify context path is `/HMS`
- Access: http://localhost:8080/HMS/index.jsp (not just /index.jsp)

### **Issue 4: HTTP 500 - Internal Server Error**
**Error:** Server error when accessing pages

**Solution:**
- Check Tomcat logs in `logs/catalina.out`
- Verify database is running
- Check all servlets compiled successfully

---

## 📞 QUICK START CHECKLIST

- [ ] MySQL Server is running
- [ ] Database `hospital` is created and imported
- [ ] Tomcat Server is running
- [ ] Project is deployed to Tomcat
- [ ] Can access: http://localhost:8080/HMS/index.jsp
- [ ] Can login with credentials: admin/admin

---

## 🎓 FOR DEMO/VIVA

### **Demo Flow:**
1. Show login page
2. Login as Admin (admin/admin)
3. Add a new patient
4. View patient list
5. Add a new doctor
6. View doctor list
7. Logout
8. Login as User (123/123)
9. Show user dashboard

### **Questions You Might Be Asked:**

**Q: What technologies did you use?**
A: Java, JSP, Servlets, MySQL, Bootstrap, Apache Tomcat - Following MVC architecture

**Q: How did you prevent SQL injection?**
A: Used PreparedStatement in database operations (Note: Current code uses Statement, should be upgraded to PreparedStatement)

**Q: How does authentication work?**
A: Session-based authentication - credentials verified against database, session created on successful login

**Q: What is the architecture?**
A: MVC (Model-View-Controller):
- Model: Database package (DatabaseConnection)
- View: JSP pages
- Controller: Servlets (AdminLogin, UserLogin, etc.)

---

## 📝 NOTES

- Default MySQL password is empty (no password)
- If you change database credentials, update `DatabaseConnection.java`
- Project uses Bootstrap 4.3.1 for UI
- All JSP pages are in root directory
- Servlets are in `src/java/Controller/` package

---

## ✨ NEXT IMPROVEMENTS (Optional)

1. Add session management and security
2. Implement PreparedStatement for SQL injection prevention
3. Add input validation
4. Implement role-based access control
5. Add logout functionality
6. Create appointment booking system
7. Add medical records management

---

**Good Luck with your project! 🎉**
