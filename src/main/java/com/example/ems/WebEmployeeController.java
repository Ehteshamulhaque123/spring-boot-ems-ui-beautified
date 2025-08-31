package com.example.ems;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
public class WebEmployeeController {
  private final EmployeeService service;
  private final EmployeeRepository repo;

  public WebEmployeeController(EmployeeService service, EmployeeRepository repo) {
    this.service = service;
    this.repo = repo;
  }

  @GetMapping
  public String list(
      @RequestParam(required = false) String q,
      @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
      Model model) {
    var page = service.list(q, pageable);
    model.addAttribute("employees", page);
    model.addAttribute("q", q);
    model.addAttribute("totalCount", page.getTotalElements());
    return "employees/list";
  }

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("employee", new Employee());
    return "employees/form";
  }

  @PostMapping
  public String save(@Valid @ModelAttribute Employee employee, BindingResult result, RedirectAttributes ra) {
    if (result.hasErrors()) return "employees/form";
    service.save(employee);
    ra.addFlashAttribute("flashSuccess", "Employee created successfully.");
    return "redirect:/employees";
  }

  @GetMapping("/{id}/edit")
  public String editForm(@PathVariable Long id, Model model) {
    model.addAttribute("employee", service.get(id));
    return "employees/form";
  }

  @PostMapping("/{id}")
  public String update(@PathVariable Long id, @Valid @ModelAttribute Employee employee, BindingResult result, RedirectAttributes ra) {
    if (result.hasErrors()) return "employees/form";
    employee.setId(id);
    service.save(employee);
    ra.addFlashAttribute("flashSuccess", "Employee updated.");
    return "redirect:/employees";
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Long id, RedirectAttributes ra) {
    service.delete(id);
    ra.addFlashAttribute("flashSuccess", "Employee deleted.");
    return "redirect:/employees";
  }

  @GetMapping("/export")
  public ResponseEntity<byte[]> exportCsv() {
    List<Employee> all = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    String csv = "id,name,department,salary\n" + all.stream()
      .map(e -> String.format("%d,%s,%s,%s",
          e.getId(),
          escape(e.getName()),
          escape(e.getDepartment()),
          e.getSalary() == null ? "" : e.getSalary().toString()))
      .collect(Collectors.joining("\n"));
    byte[] body = csv.getBytes(StandardCharsets.UTF_8);
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.csv")
      .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
      .body(body);
  }

  @PostMapping("/seed")
  public String seed(RedirectAttributes ra) {
    if (repo.count() == 0) {
      Employee a = new Employee(); a.setName("Alice"); a.setDepartment("HR"); a.setSalary(75000.0); repo.save(a);
      Employee b = new Employee(); b.setName("Bob"); b.setDepartment("IT"); b.setSalary(90000.0); repo.save(b);
      Employee c = new Employee(); c.setName("Charlie"); c.setDepartment("Finance"); c.setSalary(88000.0); repo.save(c);
      ra.addFlashAttribute("flashSuccess", "Seeded 3 sample employees.");
    } else {
      ra.addFlashAttribute("flashSuccess", "Employees already exist.");
    }
    return "redirect:/employees";
  }

  private String escape(String s) {
    if (s == null) return "";
    if (s.contains(",") || s.contains("\"") ) {
      return "\"" + s.replace("\"", "\"\"") + "\"";
    }
    return s;
  }
}
