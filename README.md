# BITP3123-Group-Project-Smart-Campus
HAMSHINI SHARMILAA A/P RANGATHURAI, B032510570 
NUR QAMARIENA BINTI MOHAMED RASHID , B032510588 
NURUL AIN QAMARIAH BINTI ZAMZILAFAZLIN, B032510636 
NIK AFIQAH NAJIHAH BINTI NIK SUKIMAN, B032510606 
ANIS SYAFIQAH BINTI SYAFRIZAN, B032510625

# Project Overview
SmartCampus Connect System is a distributed campus management system that provides several services to manage campus activities efficiently. 
The system is designed using a service-based architecture where each module works independently and communicates through REST API. It allows students and administrators to manage student profiles, course enrolment, notifications, library activities, and reporting analytics in a more organized way.
The main purpose of this system is to improve campus service management by separating different functionalities into individual services that can be maintained and updated easily.

---
# Modules Available:

# 1. Student Profile Service Module
The Student Profile Service (Port 8081) is the main service in the SmartCampus system. It manages student information, including student IDs, personal details, and account status, using its own MySQL database. This service acts as the main source of student data for the other services in the system.

Other services, such as the Course Enrolment Service and Library Booking Service, check this service to confirm that a student ID is valid and active before completing any request. If the student information is invalid or the account is inactive, the request is rejected. This helps keep the data accurate and prevents invalid or duplicate records from being created.


## Retrieve All Students

<p align="center">
<img src="https://github.com/user-attachments/assets/c84dd275-e7bc-4979-a8a6-6bac63f9d907" width="650">
</p>

---

## Retrieve Student by ID

<p align="center">
<img src="https://github.com/user-attachments/assets/ff5a4fa1-387f-4e4e-a22b-506bb6923e7a" width="650">
</p>

---

## MySQL Data Persistence

<p align="center">
<img src="https://github.com/user-attachments/assets/4f596807-f153-4af5-bd3c-e21e2e81c314" width="500">
</p>

---

# 2. Course Enrolment Service Module
The Course Enrolment Service (Port 8082) manages the academic registration lifecycle, allowing students to enroll in specific campus courses. To maintain strict data integrity, this service does not operate in isolation; instead, it relies on a multithreaded synchronous-to-asynchronous hybrid architecture. Upon receiving an enrollment request, it utilizes a background worker thread via Java's ExecutorService to seamlessly query the Student Profile Service (Port 8081) via an HTTP GET request. This step ensures that the student record actually exists and is active before changing the enrollment status from pending to CONFIRMED. Once validation succeeds and database persistence is complete, the service emits a fire-and-forget asynchronous data payload to the network to trigger student alerts.

## MySQL
<p align="center">
<img width="657" height="216" alt="image" src="https://github.com/user-attachments/assets/37765d08-1e2d-46f6-aa30-3307fa598683" />
</p>

---
## Retrieves a list of all course enrollment states across the campus
<p align="center">
<img width="678" height="807" alt="image" src="https://github.com/user-attachments/assets/d4d14dcb-08e9-4905-8cf2-111efb2a9e06" />
</p>

## Enrol student course
<p align="center">
<img width="1572" height="400" alt="image" src="https://github.com/user-attachments/assets/1f6b9328-9558-4234-b1a0-a748e12375a9" />
</p>

## Check students enrolment status by Enrolment ID
<p align="center">
<img width="1576" height="393" alt="image" src="https://github.com/user-attachments/assets/03309fd1-19a4-4fd9-ada3-1b543c3eb843" />
</p>

---
# 3. Library Booking Service Module
The Library Book Booking Service (Port 8083) manages the academic literature reservation lifecycle, allowing students to book and reserve library textbooks and reference materials. To maintain strict data integrity, this service does not operate in isolation; instead, it relies on a multithreaded synchronous-to-asynchronous hybrid architecture. Upon receiving a booking request, it utilizes a background worker thread via Java's ExecutorService to seamlessly query the Student Profile Service (Port 8081) via an HTTP GET request. This step ensures that the student record actually exists and is active before changing the book reservation status from pending to RESERVED. Once validation succeeds and database persistence is complete, the service emits a fire-and-forget asynchronous data payload to the network to trigger student alerts.

## MySQL
<p align="center">
<img width="691" height="201" alt="image" src="https://github.com/user-attachments/assets/3ebb2f0f-be6d-4c04-9f7c-3415f8914057" />
</p>

---
## Retrieves a list of all library book bookings
<p align="center">
<img width="640" height="971" alt="Screenshot 2026-07-08 221622" src="https://github.com/user-attachments/assets/2bbfb3ca-1b44-47aa-92e6-f04292c1e843" />
</p>

## Book a library book
<p align="center">
<img width="723" height="426" alt="Screenshot 2026-07-08 214825" src="https://github.com/user-attachments/assets/985588eb-dbf1-405b-ac0a-455b8469ebab" />
</p>

## Check book reservation status by Booking ID
<p align="center">
<img width="642" height="423" alt="Screenshot 2026-07-08 215333" src="https://github.com/user-attachments/assets/976b3fa9-43e6-4a49-b8be-048db0f76960" />
</p>

---
# 4. Notification Service Module
The Notification Service (Port 8084) is a independent module responsible for managing and tracking automated campus alerts, such as Email and SMS messages. To ensure maximum system performance, this service operates using a non-blocking communication model. It exposes a specific URL endpoint that waits for other modules (such as the Course Enrolment or Library Book Booking services) to send message data over the network. When a new message package arrives, the service extracts the details, creates a unique 8-character tracking number using UUID logic, and saves the record into a MySQL database table. At the same time, it prints the transaction details directly onto the computer terminal screen to verify that the communication was successfully processed.

## Enrollment Course Notification
<img width="601" height="113" alt="image" src="https://github.com/user-attachments/assets/bde35e14-3060-46b5-8602-1bdace4d0784" />

## Booking Book Notification
<img width="557" height="111" alt="image" src="https://github.com/user-attachments/assets/7312ef90-74be-44ad-8d96-40085d368a42" />

## MySQL Database Notification
<img width="636" height="240" alt="image_2026-07-08_23-42-14" src="https://github.com/user-attachments/assets/6056316a-c922-4220-a8ee-087b31acd1b3" />


---
# 5. Reporting Analytics Service

SmartCampus Connect System. This service is responsible for collecting and processing important system information to provide administrators with a clear overview of campus activities. Using REST API communication, the service retrieves relevant data from other modules and processes the information to generate a summarized analytics report. The generated summary allows administrators to monitor key statistics and support better decision-making across the campus management system.

## Function Available

#### Summary Report
The summary report provides a quick overview of system data by displaying collected information from different services.

## Postman Result:

#### Get Summary Report

Method: GET

---
# Technologies Used
- Java Spring Boot
- REST API
- Maven
- MySQL Database
- Postman
- GitHub

---
# Presentation video Link
https://youtu.be/q1ECzg_3nc4




