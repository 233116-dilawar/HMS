# 🏗️ Hospital Management System - MVC Architecture Documentation

## 📐 Architecture Overview

This Hospital Management System follows the **Model-View-Controller (MVC)** architectural pattern, which separates the application into three interconnected components:

### **1. Model Layer** (`src/java/Model/`)
Represents the data and business logic of the application.

**Classes:**
- `Patient.java` - Patient entity with properties (fname, lname, gender, city, email, age, address, date, mobile)
- `Doctor.java` - Doctor entity with properties (id, fname, lname, gender, mobile, city, email, age, address, registrationDate, qualification)
- `Admin.java` - Admin entity for authentication (username, password)
- `User.java` - User entity for authentication (username, password, email, mobile)
- `Receptionist.java` - Receptionist entity
- `Worker.java` - Worker entity

### **2. View Layer** (JSP files in root directory)
Handles the presentation and user interface.

**Admin Views:**
- `adminLogin.jsp` - Admin login page
- `AdminHome.jsp` - Admin dashboard
- `addDoctor.jsp` - Add new doctor form
- `addPatient.jsp` - Add new patient form
- `addRecp.jsp` - Add new receptionist form
- `addWorker.jsp` - Add new worker form
- `adminDoctorList.jsp` - List all doctors
- `adminPatientList.jsp` - List all patients
- `adminRecpList.jsp` - List all receptionists
- `adminWorkerList.jsp` - List all workers
- `adminAppointmentList.jsp` - List all appointments

**User Views:**
- `index.jsp` - User login page
- `UserHome.jsp` - User dashboard
- `listPatient.jsp` - View patient list
- `updatePatient.jsp` - Update patient information
- `deletePatient.jsp` - Delete patient

### **3. Controller Layer** (`src/java/Controller/`)
Handles user requests and coordinates between Model and View.

**Servlets:**
- `AdminLogin.java` - Handles admin authentication
- `UserLogin.java` - Handles user authentication
- `AddDoctor.java` - Processes add doctor requests
- `AddPatient.java` - Processes add patient requests
- `AddRecp.java` - Processes add receptionist requests
- `AddWorker.java` - Processes add worker requests
- `PatientServlet.java` - Handles patient CRUD operations
- `updatePatient.java` - Processes patient updates
- `Logout.java` - Handles user/admin logout

### **4. DAO Layer** (`src/java/DAO/`) - **NEW!**
Data Access Object pattern for database operations.

**Classes:**
- `PatientDAO.java` - Patient database operations (CRUD)
- `DoctorDAO.java` - Doctor database operations (CRUD)
- `AdminDAO.java` - Admin authentication operations
- `UserDAO.java` - User authentication operations

### **5. Database Layer** (`src/java/Database/`)
Manages database connections.

**Classes:**
- `DatabaseConnection.java` - Provides database connection using JDBC

---

## 🔄 Request Flow

```
User Request → Controller (Servlet) → DAO → Database
                    ↓
              Model (POJO)
                    ↓
              View (JSP) → Response
```

### Example: Admin Login Flow

1. **User** enters credentials in `adminLogin.jsp`
2. **Form** submits to `AdminLogin` servlet
3. **Controller** (`AdminLogin.java`):
   - Receives request parameters
   - Calls `AdminDAO.validateAdmin(username, password)`
4. **DAO** (`AdminDAO.java`):
   - Executes SQL query using PreparedStatement
   - Returns `Admin` model object
5. **Controller**:
   - Creates session if authentication successful
   - Redirects to `AdminHome.jsp`
6. **View** (`AdminHome.jsp`) displays admin dashboard

---

## 🎯 Key Improvements in MVC Structure

### **Before (Old Structure):**
❌ Controllers directly accessed database
❌ SQL queries mixed with business logic
❌ No separation of concerns
❌ Difficult to test and maintain
❌ SQL injection vulnerabilities

### **After (New MVC Structure):**
✅ Clear separation of concerns
✅ DAO layer handles all database operations
✅ PreparedStatement prevents SQL injection
✅ Model classes represent data entities
✅ Controllers are thin and focused
✅ Easy to test and maintain
✅ Reusable DAO methods

---

## 🔐 Security Features

1. **PreparedStatement** - Prevents SQL injection attacks
2. **Session Management** - Secure user authentication
3. **Input Validation** - Validates user inputs
4. **Session Timeout** - 30-minute inactivity timeout
5. **Role-Based Access** - Separate admin and user roles

---

## 📊 Database Schema

**Tables:**
- `adminreg` - Admin credentials
- `login` - User credentials
- `patient` - Patient information
- `doctor` - Doctor information
- `recp` - Receptionist information
- `worker` - Worker information
- `medicine` - Medicine inventory (if applicable)

---

## 🚀 Running the Application

### **Prerequisites:**
1. MySQL Server running on localhost:3306
2. Database `hospital` created and imported
3. Apache Tomcat Server
4. NetBeans IDE (recommended)

### **Steps:**
1. Open NetBeans
2. File → Open Project
3. Select Hospital-Management-System-main folder
4. Right-click project → Clean and Build
5. Right-click project → Run
6. Access: http://localhost:8080/HMS/

---

## 📝 Login Credentials

**Admin:**
- Username: `admin`
- Password: `admin`

**User:**
- Username: `123`
- Password: `123`

---

## 🛠️ Technology Stack

- **Backend:** Java, JSP, Servlets
- **Database:** MySQL
- **Server:** Apache Tomcat
- **Architecture:** MVC with DAO Pattern
- **Frontend:** Bootstrap 4, HTML, CSS, JavaScript

---

## 📚 Design Patterns Used

1. **MVC (Model-View-Controller)** - Overall architecture
2. **DAO (Data Access Object)** - Database abstraction
3. **Singleton** - Database connection management
4. **Front Controller** - Servlet-based request handling

---

## 🔄 Future Enhancements

1. Add Service layer between Controller and DAO
2. Implement password hashing (BCrypt)
3. Add input validation framework
4. Implement logging (Log4j)
5. Add unit tests (JUnit)
6. Implement RESTful API
7. Add pagination for large datasets
8. Implement file upload for patient documents

---

**Created:** 2025-12-29  
**Version:** 2.0 (MVC Refactored)
