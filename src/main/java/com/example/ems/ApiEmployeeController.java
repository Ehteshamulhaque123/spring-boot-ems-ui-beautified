package com.example.ems;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class ApiEmployeeController {
  private final EmployeeService service;
  public ApiEmployeeController(EmployeeService service) { this.service = service; }

  @GetMapping
  public Page<Employee> list(@RequestParam(required = false) String q, Pageable pageable) {
    return service.list(q, pageable);
  }

  @PostMapping
  public Employee create(@RequestBody Employee e) { return service.save(e); }

  @GetMapping("/{id}")
  public Employee get(@PathVariable Long id) { return service.get(id); }

  @PutMapping("/{id}")
  public Employee update(@PathVariable Long id, @RequestBody Employee e) {
    Employee existing = service.get(id);
    existing.setName(e.getName());
    existing.setDepartment(e.getDepartment());
    existing.setSalary(e.getSalary());
    return service.save(existing);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) { service.delete(id); }
}
