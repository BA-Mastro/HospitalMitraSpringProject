package com.binary.myhospital.services;

import com.binary.myhospital.entities.Department;
import com.binary.myhospital.entities.Doctor;
import com.binary.myhospital.exceptions.DepartmentNotFoundException;
import com.binary.myhospital.repositories.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department, Long id) {
        Optional<Department> oldDepartOpt = departmentRepository.findById(id);
        if (oldDepartOpt.isEmpty()) {
            throw new DepartmentNotFoundException("Department with ID = "+ id +" not found!");
        }
        Department depart = oldDepartOpt.get();
        depart.setDepartmentName(department.getDepartmentName());
        depart.setFloorNumber(department.getFloorNumber());
        depart.setDoctors(department.getDoctors());
        return departmentRepository.save(depart);
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentRepository.findByIdWithDoctors(id).orElseThrow(()
                -> new DepartmentNotFoundException("Department with ID = "+ id +" not found!"));
    }

    @Override
    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }
}
