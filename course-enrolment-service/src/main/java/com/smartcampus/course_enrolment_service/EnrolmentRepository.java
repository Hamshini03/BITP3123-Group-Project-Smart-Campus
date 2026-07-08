package com.smartcampus.course_enrolment_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EnrolmentRepository extends JpaRepository<Enrolment, String> {

}
