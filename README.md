# 📊 Student Result Management System (JDBC & Collections)

### 📌 Course Information
- **Course Code:** SE-409
- **Course Title:** Advanced Enterprise Java
- **Assignment:** Student Result System using JDBC and Java Collections

---

## 🚀 Project Overview
This project is a comprehensive **Student Result Management System** that allows users to input student data, calculate their total marks and grades, and store the information permanently in a **MySQL Database** using **JDBC**. It demonstrates the practical use of Java Collections and Database connectivity.

---

## ✨ Features & Requirements Fulfilled
As per the assignment requirements, the following features are implemented:
- ✅ **Java Collections Usage:**
    - `Array`: Used to store student names sequentially.
    - `HashMap<Integer, Integer[]>`: Used to store student IDs as unique keys and an array of marks (3 subjects) as values.
- ✅ **Logic & Calculation:**
    - Automatically calculates **Total Marks**.
    - Assigns **Grades** based on the average marks (e.g., A+, A, B, F).
- ✅ **JDBC Integration:**
    - Establishes a connection to a MySQL database.
    - Uses `PreparedStatement` to insert student records (ID, Name, Marks, Total, Grade).
- ✅ **Data Retrieval:**
    - Includes a method to fetch and display all records directly from the database console.

---

## 🛠️ Tech Stack
- **Language:** Java (JDK 17+)
- **Database:** MySQL (via XAMPP)
- **API:** JDBC (Java Database Connectivity)
- **Driver:** `mysql-connector-j-8.4.0.jar`

---

## 📂 Database Schema
To run this project, create a database named `student_db` and execute the following SQL:
```sql
CREATE TABLE student_results (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    marks_sub1 INT,
    marks_sub2 INT,
    marks_sub3 INT,
    total INT,
    grade VARCHAR(5)
);