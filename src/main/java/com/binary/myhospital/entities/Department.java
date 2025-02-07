package com.binary.myhospital.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long departmentId;
    private String departmentName;
    private int floorNumber;
//    this is saying this table is mapped to another(doctor) table by its id
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Doctor> doctors;

    public Department(){
        this.doctors = new ArrayList<>();
    }

    public Department(long departmentId, String departmentName, int floorNumber, List<Doctor> doctors) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.floorNumber = floorNumber;
        this.doctors = doctors;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Departments: {department_id= " + departmentId +
                ", department_name= " + departmentName +
                ", floor_number= " + floorNumber+
                ", doctors = "+ doctors +
                "}";
    }
}
