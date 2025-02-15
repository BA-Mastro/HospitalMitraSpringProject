package com.binary.myhospital.controllers;

import com.binary.myhospital.dto.DepartmentDto;
import com.binary.myhospital.entities.Department;
import com.binary.myhospital.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAll(){
        return new ResponseEntity<>(departmentService.getAllDepartment(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentByID(@PathVariable long id){
        Department depart = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(depart,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        Department depart = departmentService.createDepartment(department);
        return new ResponseEntity<>(depart,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable long id, @RequestBody Department department){
        Department depart = departmentService.updateDepartment(department, id);
        return new ResponseEntity<>(depart,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable long id){
        departmentService.deleteDepartment(id);
        String message = "Department "+id+" is deleted";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @GetMapping("/{id}/with-doctors")
    public ResponseEntity<DepartmentDto> getDepartmentWithDoctors(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentWithDoctors(id));
    }
}
