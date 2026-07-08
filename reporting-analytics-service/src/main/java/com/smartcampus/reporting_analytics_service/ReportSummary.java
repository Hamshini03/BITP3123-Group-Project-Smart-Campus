package com.smartcampus.reporting_analytics_service;

import java.util.Map;

public class ReportSummary {
    private int totalStudents;
    private int activeStudents;
    private int totalEnrolments;
    private int confirmedEnrolments;
    private int totalLibraryBookings;
    private int reservedBookings;
    private String mostPopularProgramme;
    private Map<String, Integer> enrolmentsPerProgramme;

    public ReportSummary() {}

    public ReportSummary(int totalStudents, int activeStudents, int totalEnrolments, 
                         int confirmedEnrolments, int totalLibraryBookings, 
                         int reservedBookings, String mostPopularProgramme,
                         Map<String, Integer> enrolmentsPerProgramme) {
        this.totalStudents = totalStudents;
        this.activeStudents = activeStudents;
        this.totalEnrolments = totalEnrolments;
        this.confirmedEnrolments = confirmedEnrolments;
        this.totalLibraryBookings = totalLibraryBookings;
        this.reservedBookings = reservedBookings;
        this.mostPopularProgramme = mostPopularProgramme;
        this.enrolmentsPerProgramme = enrolmentsPerProgramme;
    }

    // Getters and Setters
    public int getTotalStudents() { return totalStudents; }
    public void setTotalStudents(int totalStudents) { this.totalStudents = totalStudents; }

    public int getActiveStudents() { return activeStudents; }
    public void setActiveStudents(int activeStudents) { this.activeStudents = activeStudents; }

    public int getTotalEnrolments() { return totalEnrolments; }
    public void setTotalEnrolments(int totalEnrolments) { this.totalEnrolments = totalEnrolments; }

    public int getConfirmedEnrolments() { return confirmedEnrolments; }
    public void setConfirmedEnrolments(int confirmedEnrolments) { this.confirmedEnrolments = confirmedEnrolments; }

    public int getTotalLibraryBookings() { return totalLibraryBookings; }
    public void setTotalLibraryBookings(int totalLibraryBookings) { this.totalLibraryBookings = totalLibraryBookings; }

    public int getReservedBookings() { return reservedBookings; }
    public void setReservedBookings(int reservedBookings) { this.reservedBookings = reservedBookings; }

    public String getMostPopularProgramme() { return mostPopularProgramme; }
    public void setMostPopularProgramme(String mostPopularProgramme) { this.mostPopularProgramme = mostPopularProgramme; }

    public Map<String, Integer> getEnrolmentsPerProgramme() { return enrolmentsPerProgramme; }
    public void setEnrolmentsPerProgramme(Map<String, Integer> enrolmentsPerProgramme) { this.enrolmentsPerProgramme = enrolmentsPerProgramme; }
}