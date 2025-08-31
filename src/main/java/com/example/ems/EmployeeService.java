package com.example.ems;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
  private final EmployeeRepository repo;
  public EmployeeService(EmployeeRepository repo) { this.repo = repo; }

  public Page<Employee> list(String q, Pageable pageable) {
    if (q == null || q.isBlank()) return repo.findAll(pageable);
    return repo.findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(q, q, pageable);
  }

  public Employee get(Long id) { return repo.findById(id).orElseThrow(); }
  public Employee save(Employee e) { return repo.save(e); }
  public void delete(Long id) { repo.deleteById(id); }
}
