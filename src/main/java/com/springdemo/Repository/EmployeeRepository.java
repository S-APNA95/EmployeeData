package com.springdemo.Repository;


import com.springdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// no need to add @repository because jpaRepository has built in
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
