package com.binary.myhospital.services;

import com.binary.myhospital.entities.Department;
import com.binary.myhospital.entities.Doctor;
import com.binary.myhospital.exceptions.DoctorNotFoundException;
import com.binary.myhospital.repositories.DepartmentRepository;
import com.binary.myhospital.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        if (doctor.getDepartment() == null || doctor.getDepartment().getDepartmentId() == 0) {
            throw new RuntimeException("Department must be provided.");
        }

        Department department = departmentRepository.findById(doctor.getDepartment().getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        doctor.setDepartment(department);
        department.getDoctors().add(doctor); // Ensure doctor list is updated

        departmentRepository.save(department); // Save department so list is updated
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor, long id) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        if(doctorOpt.isEmpty()){
            throw new DoctorNotFoundException("Doctor with ID = "+id+" not found!");
        }
        Doctor doc = doctorOpt.get();
        doc.setFirst_name(doctor.getFirst_name());
        doc.setLast_name(doctor.getLast_name());
        doc.setEmail(doctor.getEmail());
        doc.setPhone_number(doctor.getPhone_number());
        doc.setSpecialization(doctor.getSpecialization());
        doc.setDepartment(doctor.getDepartment());
        return doctorRepository.save(doc);
    }

    @Override
    public Doctor getDoctorById(long id) {
        return doctorRepository.findById(id).orElseThrow(() ->
                new DoctorNotFoundException("Doctor with ID = "+id+" not found!"));
    }

    @Override
    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }
}
