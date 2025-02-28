package com.binary.myhospital.services;

import com.binary.myhospital.dto.DoctorDto;
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
    public Doctor createDoctor(DoctorDto doctorDto) {
        if (doctorDto.getDepartment_id() == null || doctorDto.getDepartment_id() == 0) {
            throw new RuntimeException("Department must be provided.");
        }

        Department department = departmentRepository.findById(doctorDto.getDepartment_id())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Doctor doc = new Doctor();
        doc.setFirst_name(doctorDto.getFirstName());
        doc.setLast_name(doctorDto.getLastName());
        doc.setPhone_number(doctorDto.getPhoneNumber());
        doc.setEmail(doctorDto.getEmail());
        doc.setSpecialization(doctorDto.getSpecialization());
        doc.setDepartment(department);

        return doctorRepository.save(doc);
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
