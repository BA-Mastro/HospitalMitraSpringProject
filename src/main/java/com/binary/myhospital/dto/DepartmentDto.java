package com.binary.myhospital.dto;

import java.util.List;

public class DepartmentDto {
    private final long departmentId;
    private final String departmentName;
    private final int floorNumber;
    private final List<DoctorDto> doctors;

    public DepartmentDto(long departmentId, String departmentName, int floorNumber, List<DoctorDto> doctors) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.floorNumber = floorNumber;
        this.doctors = doctors;
    }

    // Getters
    public long getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }
    public int getFloorNumber() { return floorNumber; }
    public List<DoctorDto> getDoctors() { return doctors; }
}