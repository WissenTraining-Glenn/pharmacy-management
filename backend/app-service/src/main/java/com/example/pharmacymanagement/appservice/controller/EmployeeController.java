package com.example.pharmacymanagement.appservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.pharmacymanagement.appservice.repository.EmployeeRepo;
import com.example.pharmacymanagement.appservice.dto.Response;
import com.example.pharmacymanagement.appservice.entity.Employee;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public ResponseEntity<Response> getEmployees(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if(size != null && (size<10 || size>50)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size should be between 10 and 50");
        if(page != null && page<0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page should be greater than or equal to 0");
        if(size == null) size = 10;
        if(page == null) page = 0;
        List<Employee> employees = employeeRepo.findAll();
        return ResponseEntity.ok(Response.builder()
                .data(employees.stream().skip(page * size).limit(size).toList())
                .page(page)
                .size(size)
                .totalPages((int) Math.ceil((double) employees.size() / size))
                .hasNext(employees.size() > (page + 1) * size)
                .hasPrevious(page > 0)
                .build());
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Response> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(Response.builder()
                .data(employeeRepo.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Employee with id: " + id + " not found"))
                )
                .build());
    }

    @PostMapping("/employee")
    public ResponseEntity<Response> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(Response.builder()
                .data(employeeRepo.save(employee))
                .build()); 
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<Response> updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) {
        Employee existingEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee with id: " + id + " not found"));
        if(!employee.getName().isBlank()) existingEmployee.setName(employee.getName());
        return ResponseEntity.ok(Response.builder()
                .data(employeeRepo.save(existingEmployee))
                .build());
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable Integer id) {
        if(employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            return ResponseEntity.ok(Response.builder()
                    .build());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id: " + id + " not found");
        }
    }
}