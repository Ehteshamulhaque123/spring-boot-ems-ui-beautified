package com.example.ems;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Page<Employee> findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(String name, String dept, Pageable pageable);
}
