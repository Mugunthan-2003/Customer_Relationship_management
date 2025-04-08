package com.example.crm.services;

import com.example.crm.entities.Employee;
import com.example.crm.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    private Employee employee;

    @BeforeEach
    void setup() {
        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setName("Sam");
        employee.setEmail("sam@gmail.com");
        employee.setPhoneNumber("6891482911");
    }

    @Test
    void testAddEmployee() {
        when(repository.save(any(Employee.class))).thenReturn(employee);
        Employee addedEmployee = service.addEmployee(employee);
        assertNotNull(addedEmployee);
        assertEquals(employee.getName(), addedEmployee.getName());
        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    void testUpdateEmployee() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(repository.save(any(Employee.class))).thenReturn(employee);
        Employee updatedEmployee = service.updateEmployee(employee);
        assertNotNull(updatedEmployee);
        assertEquals(employee.getName(), updatedEmployee.getName());
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteEmployee(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = List.of(employee);
        when(repository.findAll()).thenReturn(employees);
        List<Employee> result = service.getAllEmployees();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));
        Employee result = service.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals(employee.getName(), result.getName());
        verify(repository, times(1)).findById(anyLong());
    }
}
