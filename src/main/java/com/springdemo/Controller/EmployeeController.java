package com.springdemo.Controller;

import com.springdemo.Service.impl.EmployeeService;
import com.springdemo.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/employees")
public class EmployeeController {
    
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }
    
    // build create employee REST API
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){  // to generate respose to restapi -ResponseEntity is generic class
       Employee e = employeeService.saveEmployee(employee);
        return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
    }
    //build get all emeployee RestApi
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    //build get by id emeployee RestApi
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        Employee e = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<Employee>(e, HttpStatus.OK);
    }
    //build update  emeployee RestApi

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id
                                                , @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id),HttpStatus.OK);

    }
    //build delete employee RestApi
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee (@PathVariable("id") long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Deleted successfully.", HttpStatus.OK);
    }

}
