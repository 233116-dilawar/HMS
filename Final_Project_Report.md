# FINAL PROJECT REPORT
## Hospital Management System

**Student Name(s):** [Your Name Here]
**Registration Number(s):** [Your Reg No Here]
**Section:** [Your Section]
**Course Title:** Web Systems and Technologies
**Project Title:** Hospital Management System

---

## Chapter 1: SRS Document and UML Diagrams

*(Please insert your existing SRS document and UML diagrams here as required by the assignment logic. This section should include your use case diagrams, class diagrams, and sequence diagrams from the previous assignment.)*

---

## Chapter 2: Generated Java Code

### Code Generation Method
The Java implementation for this project was generated based on the UML Design Models developed in the previous phase. 
- **Tool/Method Used:** Manual coding based on MVC (Model-View-Controller) architectural patterns derived from the specific Class Diagrams.
- **Architectural Pattern:** The system strictly follows the MVC pattern to separate concerns between Data (Model), User Interface (View), and Logic (Controller).

### Core Generated Classes
Based on the Class Diagram, the following core classes were implemented:
- **Model Layer:** `Patient.java`, `Doctor.java`, `Admin.java`, `User.java`, `Receptionist.java`, `Worker.java`.
- **Database Layer:** `DatabaseConnection.java` (Singleton Pattern).
- **Data Access Object (DAO):** `PatientDAO`, `DoctorDAO`, `AdminDAO`.

---

## Chapter 3: Project Implementation Details

### 3.1 Project Structure
The project is a **Java Web Application** built using **JSP (JavaServer Pages), Servlets, and MySQL**.

#### Directory Structure
- **src/java/Model:** POJO classes representing database entities.
- **src/java/Controller:** Servlets handling HTTP requests (`AdminLogin`, `PatientServlet`, `AddDoctor`, etc.).
- **src/java/DAO:** Data Access Objects isolating database logic.
- **Web Pages (Root):** JSP files for the frontend (e.g., `AdminHome.jsp`, `userLogin.jsp`, `addPatient.jsp`).
- **Web Pages/css:** Custom styles and Bootstrap integration.

### 3.2 Key Features Implemented
1.  **Authentication & Authorization:**
    - Separate login portals for Administrators and Users.
    - Session management to enforce security.
2.  **Patient Management:**
    - Full CRUD (Create, Read, Update, Delete) capabilities.
    - Admin can view, add, search, and discharge patients.
3.  **Staff Management:**
    - Modules to add and view Doctors, Receptionists, and Workers.
4.  **Dashboard:**
    - Real-time counters showing total counts of patients, doctors, etc.

### 3.3 Database Design
The application uses a MySQL database (`hospital`) with the following key tables:
- `patient`: Stores patient demographics and admission details.
- `doctor`: Stores doctor qualifications and details.
- `adminreg`: Stores admin credentials.
- `login`: Stores user credentials.

---

## Chapter 4: Build and Unit Testing

### 4.1 Build Process
The project uses **Maven** for build automation and dependency management.
- **Build Configuration:** `pom.xml`
- **Output:** `HMS.war` (Web Archive)
- **Dependencies:** `javax.servlet-api`, `jstl`, `mysql-connector-java`, `junit`.

**Build Command:**
```bash
mvn clean package
```

### 4.2 Unit Testing
Unit testing is implemented using **JUnit 4**.
- **Test Target:** `Model.Patient` class.
- **Test File:** `src/test/java/Model/PatientTest.java`

**Unit Test Case:**
The following test verifies the getters and setters of the Patient entity to ensure data integrity.

```java
package Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PatientTest {

    @Test
    public void testPatientGettersAndSetters() {
        Patient patient = new Patient();
        patient.setFname("John");
        patient.setLname("Doe");
        patient.setGender("Male");
        patient.setAge("30");
        patient.setCity("New York");
        patient.setEmail("john.doe@example.com");
        patient.setAddress("123 Main St");
        patient.setMobile("1234567890");
        
        assertEquals("John", patient.getFname());
        assertEquals("Doe", patient.getLname());
        assertEquals("Male", patient.getGender());
        assertEquals("30", patient.getAge());
        assertEquals("New York", patient.getCity());
        assertEquals("john.doe@example.com", patient.getEmail());
        assertEquals("123 Main St", patient.getAddress());
        assertEquals("1234567890", patient.getMobile());
    }
}
```

**Test Results:**
*(Run `mvn test` in your environment to generate the specific success log and paste it here. Expected output follows:)*

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running Model.PatientTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.1 s - in Model.PatientTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

---

## Chapter 5: GitHub Repository

**Repository URL:** [Insert Initial GitHub Link Here]

**Setup Instructions:**
1.  The project is version controlled using Git.
2.  Branch `main` contains the stable release.
3.  Instructor `sharifali.aulecturer@gmail.com` has been added as a contributor.

---

## Chapter 6: CI/CD Pipeline Configuration

### 6.1 Jenkins Setup
A Continuous Integration/Continuous Deployment (CI/CD) pipeline has been configured using **Jenkins**.

### 6.2 Pipeline Logic (`Jenkinsfile`)
The pipeline automation covers the following stages:

1.  **Checkout:** Pulls the latest code from the GitHub repository (`scm`).
2.  **Build with Maven:** Compiles the Java code and packages it into a `.war` file (`mvn clean package`).
3.  **Deploy to Tomcat:** 
    - Stops the Tomcat service (to release file locks).
    - Removes old deployments (`HMS` folder and `.war` file).
    - Copies the new `HMS.war` from the `target/` directory to Tomcat's `webapps/` folder.
    - Restarts the Tomcat service.

**Pipeline Visual:**
*(Insert screenshot of Jenkins Pipeline Stage View here)*

---

## Chapter 7: Application Release and Deployment

### 7.1 Deployment Environment
- **Server:** Apache Tomcat 9.0
- **Database:** MySQL 8.0
- **URL:** `http://localhost:8080/HMS`

### 7.2 Deployment Verification
- **Build Status:** Successful
- **Deployment Status:** Active
- **Access:** The application is accessible via web browser.

**(Attach Screenshots Here):**
1.  **Jenkins Success Console Output.**
2.  **Tomcat Manager App showing HMS running.**
3.  **Application Login Page in Browser.**
