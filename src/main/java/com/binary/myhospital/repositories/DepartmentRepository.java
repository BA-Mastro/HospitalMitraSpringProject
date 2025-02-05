package com.binary.myhospital.repositories;

import com.binary.myhospital.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.doctors WHERE d.departmentId = :id")
    Optional<Department> findByIdWithDoctors(@Param("id") Long id);
}
