🏨 Hotel Reservation System (Java + MySQL)
📌 Project Overview

The Hotel Reservation System is a console-based application developed using Java (JDBC) and MySQL. It allows users to manage hotel room bookings efficiently by performing operations such as reserving rooms, viewing reservations, updating details, and deleting bookings.

🎯 Problem Statement

Managing hotel reservations manually or using unstructured systems can lead to:

Data inconsistency

Booking conflicts

Difficulty in tracking customer records

Time-consuming operations

This project aims to build a structured and automated system to handle hotel reservations using a database-driven approach.

💡 Solution Approach

The system is designed using:

Java (Core + JDBC) for backend logic

MySQL for database management

Approach:

Establish connection with MySQL database using JDBC

Provide a menu-driven interface for user interaction

Perform CRUD operations:

Create → Reserve a room

Read → View reservation details

Update → Modify booking

Delete → Cancel reservation

Handle exceptions for smooth execution

⚙️ Technologies Used

Java (Core Java, JDBC)

MySQL

IDE (IntelliJ IDEA / Eclipse)

JDK 8+

🗂️ Project Structure
HotelReservationSystem.java
README.md
🧠 Features

✅ Reserve a room

✅ View all reservations

✅ Get room number using customer details

✅ Update reservation details

✅ Delete reservation

✅ Menu-driven user interface

🗄️ Database Design
Database: hotel_db
Table: reservations
Column Name	Data Type	Description
reservation_id	INT (PK, AI)	Unique reservation ID
guest_name	VARCHAR	Name of the guest
room_number	INT	Allocated room number
contact_number	VARCHAR	Guest contact details
🔄 System Workflow

User selects an option from the menu

Input is taken using Scanner

SQL query is executed using PreparedStatement

Results are displayed to the user

🔌 JDBC Connection Setup
String url = "jdbc:mysql://localhost:3306/hotel_db";
String username = "root";
String password = "your_password";
Connection connection = DriverManager.getConnection(url, username, password);
🚀 How to Run the Project

Install MySQL and create database hotel_db

Create required table (reservations)

Add MySQL JDBC Driver to your project

Update database credentials in code

Compile and run the program

⚠️ Challenges Faced

Handling SQL syntax errors

Managing database connectivity issues

Ensuring correct input handling (Scanner issues)

🔮 Future Enhancements

GUI using Java Swing or JavaFX

Online booking system (Web-based)

Payment integration

User authentication system

Room availability tracking

📚 Learning Outcomes

Understanding of JDBC connectivity

Hands-on experience with CRUD operations

Knowledge of database design

Improved debugging and error-handling skills

👨‍💻 Author

Vishal Thakare
