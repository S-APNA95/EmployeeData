package com.springdemo.Service.impl;

import com.springdemo.Repository.EmployeeRepository;
import com.springdemo.exception.ResourceNotFoundException;
import com.springdemo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private  EmployeeRepository employeeRepository;
        //@autowire not required for 1 constructor
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {  //constructor based dependencies
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
      Optional<Employee> employee = employeeRepository.findById(id);
      if(employee.isPresent()){
          return employee.get();
      }
      else{
          throw new ResourceNotFoundException("Employee" , "Id", id);
      }
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
       Employee existingEmployee =  employeeRepository.findById(id).orElseThrow(()
                              -> new ResourceNotFoundException("Employee", "Id", id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        //check whether a employee exists or not
        employeeRepository.findById(id).orElseThrow(()
                            -> new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }


}