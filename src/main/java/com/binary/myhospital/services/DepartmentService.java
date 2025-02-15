package com.binary.myhospital.services;

import com.binary.myhospital.dto.DepartmentDto;
import com.binary.myhospital.entities.Department;
import com.binary.myhospital.entities.Doctor;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartment();
    Department createDepartment(Department department);
    Department updateDepartment(Department department, Long id);
    Department getDepartmentById(long id);
    void deleteDepartment(long id);
    DepartmentDto getDepartmentWithDoctors(Long departmentId);
}
