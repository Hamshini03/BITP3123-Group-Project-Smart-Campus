# BITP3123-Group-Project-Smart-Campus
HAMSHINI SHARMILAA A/P RANGATHURAI, B032510570 
NUR QAMARIENA BINTI MOHAMED RASHID , B032510588 
NURUL AIN QAMARIAH BINTI ZAMZILAFAZLIN, B032510636 
NIK AFIQAH NAJIHAH BINTI NIK SUKIMAN, B032510606 
ANIS SYAFIQAH BINTI SYAFRIZAN, B032510625


# Student Profile Service Module
The Student Profile Service (Port 8081) serves as the foundational core and centralized "Source of Truth" for the entire SmartCampus microservices ecosystem. It is solely responsible for managing student identities, maintaining profile states, and anchoring records securely within a dedicated MySQL database. By decoupling identity management from other business logic, this module establishes a reliable registry where dependent services—such as Course Enrolment and Library Booking—can execute synchronous HTTP validation checks in real time. Without this critical checkpoint actively processing queries and verifying the legitimacy of a student's id and status field, downstream transactions across the network are automatically halted, effectively preventing orphaned data entries or fraudulent campus activities.

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

# Course Enrolment Service Module
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

