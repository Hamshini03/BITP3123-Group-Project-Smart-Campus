# BITP3123-Group-Project-Smart-Campus
HAMSHINI SHARMILAA A/P RANGATHURAI, 
B032510570 NUR QAMARIENA BINTI MOHAMED RASHID , 
B032510588 NURUL AIN QAMARIAH BINTI ZAMZILAFAZLIN, 
B032510636 NIK AFIQAH NAJIHAH BINTI NIK SUKIMAN, 
B032510606 ANIS SYAFIQAH BINTI SYAFRIZAN, B032510625


# Student Profile Service Module
The Student Profile Service (Port 8081) serves as the foundational core and centralized "Source of Truth" for the entire SmartCampus microservices ecosystem. It is solely responsible for managing student identities, maintaining profile states, and anchoring records securely within a dedicated MySQL database. By decoupling identity management from other business logic, this module establishes a reliable registry where dependent services—such as Course Enrolment and Library Booking—can execute synchronous HTTP validation checks in real time. Without this critical checkpoint actively processing queries and verifying the legitimacy of a student's id and status field, downstream transactions across the network are automatically halted, effectively preventing orphaned data entries or fraudulent campus activities.

Postman Success (Onboarding a Student): 
<img width="570" height="966" alt="image" src="https://github.com/user-attachments/assets/c84dd275-e7bc-4979-a8a6-6bac63f9d907" />



MySQL Workbench (Data Persistence): <img width="443" height="172" alt="image" src="https://github.com/user-attachments/assets/4f596807-f153-4af5-bd3c-e21e2e81c314" />


