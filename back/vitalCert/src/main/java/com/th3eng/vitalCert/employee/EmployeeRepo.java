package com.th3eng.vitalCert.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    //select employee by ssn
    @Query("SELECT e FROM Employee e WHERE e.ssn = ?1")
    Optional<Employee> findBySsn(String ssn);

}
