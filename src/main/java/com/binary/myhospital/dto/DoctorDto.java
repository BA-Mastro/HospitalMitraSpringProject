package com.binary.myhospital.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorDto{
    private long doctor_id;
    @JsonProperty("first_name")  // ✅ Ensures correct JSON mapping
    private String first_name;

    @JsonProperty("last_name")
    private String last_name;

    @JsonProperty("phone_number")
    private long phone_number;

    @JsonProperty("email")
    private String email;

    @JsonProperty("specialization")
    private String specialization;

    @JsonProperty("department_id")
    private Long department_id;

    public DoctorDto(){}

    public DoctorDto(long doctor_id, String first_name, String last_name, String specialization) {
        this.doctor_id = doctor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialization = specialization;
    }

    public DoctorDto(long doctor_id, String first_name, String last_name, long phone_number, String email, String specialization, Long department_id) {
        this.doctor_id = doctor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.specialization = specialization;
        this.department_id = department_id;
    }

    public DoctorDto(String first_name, String last_name, long phone_number, String email, String specialization, Long department_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.specialization = specialization;
        this.department_id = department_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public long getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(long doctor_id) {
        this.doctor_id = doctor_id;
    }

    @Override
    public String toString() {
        return "DoctorDto{" +
                "doctor_id=" + doctor_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone_number=" + phone_number +
                ", email='" + email + '\'' +
                ", specialization='" + specialization + '\'' +
                ", department_id=" + department_id +
                '}';
    }
}
