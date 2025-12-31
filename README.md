# 🏥 Hospital Management System (HMS)

A robust, modern Hospital Management System built with Java EE, JSP, and MySQL following the MVC (Model-View-Controller) architecture.

## 🌟 Overview

This system is designed to streamline hospital operations, including patient registration, doctor management, appointment scheduling, and administrative tasks. It provides a secure and efficient way for hospitals to manage their data.

## 🚀 Features

- **Admin Dashboard**: Comprehensive control over doctors, patients, and system staff.
- **Patient Management**: Register new patients, view history, and manage records.
- **Doctor Management**: Add and manage doctor profiles and specialties.
- **MVC Architecture**: Clean separation of concerns for better maintainability.
- **Robust Error Handling**: Graceful handling of duplicate entries (e.g., mobile numbers) and database constraints.
- **Responsive Web UI**: Built with Bootstrap for a modern, accessible interface.
- **Jenkins CI/CD**: Integrated with Jenkins for automated build and deployment pipelines.

## 🛠️ Technology Stack

- **Backend**: Java (Servlet, DAO Pattern)
- **Frontend**: JSP, HTML5, CSS3, JavaScript (Bootstrap)
- **Database**: MySQL
- **Server**: Apache Tomcat 9.0+
- **Build/CI**: Maven, Jenkins

## 📋 Prerequisites

- **Java JDK 17**
- **Apache Tomcat 9.0**
- **MySQL Server 8.0**
- **XAMPP** (Optional, for easier MySQL management)

## 🔧 Installation & Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/233116-dilawar/HMS.git
   ```

2. **Database Setup**:
   - Create a database named `hospital`.
   - Import the `hospital.sql` file provided in the root directory.
   ```sql
   CREATE DATABASE hospital;
   USE hospital;
   SOURCE hospital.sql;
   ```

3. **Deploy to Tomcat**:
   - Build the project using Maven or provided scripts.
   - Copy the generated `.war` file or exploded folder to Tomcat's `webapps` directory.

4. **Run the Application**:
   - Start Tomcat.
   - Access the system at: `http://localhost:8080/HMS/`

## 🔑 Login Credentials

- **Admin**: `admin` / `admin`
- **Default User**: `123` / `123`

## 📩 Contact & Support

For any queries or support, please contact:
**Sharif Ali (Lecturer)**
📧 [sharifali.aulecturer@gmail.com](mailto:sharifali.aulecturer@gmail.com)

---

Developed with ❤️ for Advanced Software Engineering.
