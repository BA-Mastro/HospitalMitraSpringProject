package com.binary.myhospital.services;

import com.binary.myhospital.entities.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Doctor doctor, long id);
    Doctor getDoctorById(long id);
    void deleteDoctor(long id);
}
