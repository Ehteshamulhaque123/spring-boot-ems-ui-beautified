package com.example.ems;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Employee {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String name;

  @NotBlank
  private String department;

  @Min(0)
  private Double salary;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getDepartment() { return department; }
  public void setDepartment(String department) { this.department = department; }
  public Double getSalary() { return salary; }
  public void setSalary(Double salary) { this.salary = salary; }
}
