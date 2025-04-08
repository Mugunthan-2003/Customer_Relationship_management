package com.example.crm.controllers;

import com.example.crm.entities.Employee;
import com.example.crm.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    private MockMvc mockMvc;

    private Employee employee;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setName("Sam");
        employee.setEmail("sam@gmail.com");
        employee.setPhoneNumber("6891482911");
    }

    @Test
    void testAddEmployee() throws Exception {
        when(service.addEmployee(any(Employee.class))).thenReturn(employee);
        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee)))
                .andExpect(status().isOk());
        verify(service, times(1)).addEmployee(any(Employee.class));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        when(service.updateEmployee(any(Employee.class))).thenReturn(employee);
        mockMvc.perform(put("/api/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee)))
                .andExpect(status().isOk());
        verify(service, times(1)).updateEmployee(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteEmployee(anyLong());
    }

    @Test
    void testGetAllEmployees() throws Exception {
        List<Employee> employees = List.of(employee);
        when(service.getAllEmployees()).thenReturn(employees);
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk());
        verify(service, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(service.getEmployeeById(anyLong())).thenReturn(employee);
        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).getEmployeeById(anyLong());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
