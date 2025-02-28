package com.binary.myhospital.controllers;

import com.binary.myhospital.dto.DoctorDto;
import com.binary.myhospital.entities.Doctor;
import com.binary.myhospital.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getDoctorsList(){
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Doctor> createAccount(@RequestBody DoctorDto doctor){
        Doctor newDoc = doctorService.createDoctor(doctor);

        return new ResponseEntity<>(newDoc,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorByID(@PathVariable long id){
        Doctor doctor = doctorService.getDoctorById(id);
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable long id, @RequestBody Doctor doctor){
        Doctor doc = doctorService.updateDoctor(doctor,id);
        return new ResponseEntity<>(doc, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable long id){
        doctorService.deleteDoctor(id);
        String message = "The doctor with ID "+id+ " is deleted!";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
