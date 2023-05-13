package com.th3eng.vitalCert.repository;

import com.th3eng.vitalCert.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //select citizen by ssn
    @Query("SELECT e FROM Employee e WHERE e.ssn = ?1")
    Optional<Employee> findBySsn(String ssn);
}
