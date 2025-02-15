package com.binary.myhospital.dto;

public class DoctorDto{
    private long doctorId;
    private String firstName;
    private String lastName;
    private String specialization;

    public DoctorDto(long doctorId, String firstName, String lastName, String specialization) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }

    // Getters
    public long getDoctorId() { return doctorId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSpecialization() { return specialization; }
}
