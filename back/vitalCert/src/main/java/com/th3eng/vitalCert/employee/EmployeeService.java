package com.th3eng.vitalCert.employee;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    public record EmployeeResponse(boolean success, Employee employee) {
    }

    //add employee
    public EmployeeResponse addEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepo.findBySsn(employee.getSsn());

        //check if ssn is empty or is less than 14 characters
        if (employee.getSsn().isEmpty() || employee.getSsn().length() < 14) {
            throw new IllegalStateException("ssn is empty or is less than 14 characters");
        }

        // check if employee with ssn already exists
        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("employee with ssn " + employee.getSsn() + " already exists");
        }

        //check if password is empty or is less than 8 characters
        // and if password not contains at least one digit and one Capital letter and one small letter
        if (employee.getPassword().isEmpty() || employee.getPassword().length() < 8 ||
                !employee.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
            throw new IllegalStateException("password is empty or is less than 8 characters or not contains at least one digit and one Capital letter and one small letter");
        }
        String password = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(password);
        return new EmployeeResponse(true,employeeRepo.save(employee));
    }

    //delete employee by id
    public void deleteEmployee(Long employeeId) {
        boolean exists = employeeRepo.existsById(employeeId);
        if (!exists) {
            throw new IllegalStateException("employee with id " + employeeId + " does not exists");
        }
        employeeRepo.deleteById(employeeId);
    }

    //update employee password by id
    @Transactional
    public void updateEmployee(Long employeeId, Map<String,String> passwords) {
        Optional<Employee> employeeOptional = employeeRepo.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            if (passwordEncoder.matches(passwords.get("oldPassword"), employee.getPassword())) {
                //new password validation
                if (passwords.get("newPassword").isEmpty() || passwords.get("newPassword").length() < 8 ||
                        !passwords.get("newPassword").matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
                    throw new IllegalStateException("New password is empty or is less than 8 characters or not contains at least one digit and one Capital letter and one small letter");
                }
                employee.setPassword(passwordEncoder.encode(passwords.get("newPassword")));
                employeeRepo.save(employee);
            } else {
                throw new IllegalStateException("old password is wrong");
            }
        } else{
            throw new IllegalStateException("employee with id " + employeeId + " does not exists");
        }
    }

    public EmployeeRepo getEmployeeRepo() {
        return employeeRepo;
    }
}
