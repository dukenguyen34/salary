package com.fptaptech.service;

import com.fptaptech.model.Employee;
import com.fptaptech.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) throws Exception {
        // Check for duplicate name
        Optional<Employee> existingEmployee = employeeRepository.findByName(employee.getName());
        if (existingEmployee.isPresent() && (employee.getId() == null || !employee.getId().equals(existingEmployee.get().getId()))) {
            throw new Exception("Error while creating User: Unable to create. A User with name " + employee.getName() + " already exist.");
        }
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found for id :: " + id));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}