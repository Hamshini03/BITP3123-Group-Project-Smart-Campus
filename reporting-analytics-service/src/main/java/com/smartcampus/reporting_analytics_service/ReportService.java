package com.smartcampus.reporting_analytics_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ReportSummary getSummaryReport() {
        int totalStudents = queryForCount("SELECT COUNT(*) FROM smartcampus.student");
        int activeStudents = queryForCount("SELECT COUNT(*) FROM smartcampus.student WHERE status = 'ACTIVE'");

        int totalEnrolments = queryForCount("SELECT COUNT(*) FROM smartcampus.enrolment");
        int confirmedEnrolments = queryForCount("SELECT COUNT(*) FROM smartcampus.enrolment WHERE status = 'CONFIRMED'");

        int totalBookings = queryForCount("SELECT COUNT(*) FROM smartcampus.booking");
        int reservedBookings = queryForCount("SELECT COUNT(*) FROM smartcampus.booking WHERE status = 'RESERVED'");

        String mostPopularProgramme;
        try {
            mostPopularProgramme = jdbcTemplate.queryForObject(
                "SELECT course_name FROM smartcampus.enrolment WHERE status = 'CONFIRMED' " +
                "GROUP BY course_name ORDER BY COUNT(*) DESC LIMIT 1", String.class);
        } catch (Exception e) {
            mostPopularProgramme = "No active course enrollments detected";
        }

        Map<String, Integer> enrolmentsPerProgramme = new HashMap<>();
        try {
            jdbcTemplate.query(
                "SELECT course_name, COUNT(*) as total FROM smartcampus.enrolment GROUP BY course_name",
                rs -> {
                    enrolmentsPerProgramme.put(rs.getString("course_name"), rs.getInt("total"));
                }
            );
        } catch (Exception e) {
            // Safe fallback
        }

        return new ReportSummary(
            totalStudents,
            activeStudents,
            totalEnrolments,
            confirmedEnrolments,
            totalBookings,
            reservedBookings,
            mostPopularProgramme != null ? mostPopularProgramme : "None",
            enrolmentsPerProgramme
        );
    }

    private int queryForCount(String sql) {
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
            return count != null ? count : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}