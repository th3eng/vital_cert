package com.th3eng.vitalCert.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = new EmployeeService( employeeService.getEmployeeRepo());
    }

    //get all employees
    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    //add employee
    @PostMapping(value = "/addEmployee")
    public EmployeeService.EmployeeResponse addEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        return employeeService.addEmployee(employee);
    }

    //delete employee by id
    @DeleteMapping(path = "/deleteEmployee/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
       employeeService.deleteEmployee(employeeId);
    }

    //update employee password by id
    @PutMapping(path = "/updateEmployee/{employeeId}")
    public void updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody Map<String, String> passwords) {
       employeeService.updateEmployee(employeeId,passwords);
    }

}
