# 🩺 How to Run in NetBeans

## Step 1: Open Project
NetBeans is launching now. It should automatically open this project.
If not:
1. Go to **File > Open Project**
2. Select `C:\Users\Dell\Downloads\Hospital-Management-System-main`

## Step 2: Build and Run
1. Right-click the **HMS** (or project name) in the "Projects" sidebar.
2. Select **Clean and Build**.
   - *Wait for "BUILD SUCCESSFUL" in the output window.*
3. Right-click the project again and select **Run**.

## ⚠️ Important Database Note
Since MySQL is not running yet, the application **WILL launch**, but:
- Login might fail.
- Data won't load.

To fix this for the "Live" app:
1. Install/Start MySQL.
2. Import `hospital.sql`.

## Step 3: Moving to Jenkins
Once you are happy that it runs in NetBeans:
1. Go to Jenkins (http://localhost:8080).
2. Click **Build Now** on the job we created.
