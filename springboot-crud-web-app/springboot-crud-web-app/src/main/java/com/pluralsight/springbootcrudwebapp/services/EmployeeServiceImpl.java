package com.pluralsight.springbootcrudwebapp.services;

import com.pluralsight.springbootcrudwebapp.dto.EmployeeDTO;
import com.pluralsight.springbootcrudwebapp.dto.LoginDTO;
import com.pluralsight.springbootcrudwebapp.dto.LoginMesage;
import com.pluralsight.springbootcrudwebapp.models.Employee;
import com.pluralsight.springbootcrudwebapp.models.EmployeeProjectReport;
import com.pluralsight.springbootcrudwebapp.models.EmployeeReg;
import com.pluralsight.springbootcrudwebapp.models.Project;
import com.pluralsight.springbootcrudwebapp.repositories.EmployeeRepo;
import com.pluralsight.springbootcrudwebapp.repositories.EmployeeRepository;
import com.pluralsight.springbootcrudwebapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    EmployeeDTO employeeDTO;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public void saveEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long id){
        Optional <Employee> optional= employeeRepository.findById(id);
        Employee employee=null;
        if(optional.isPresent()){
            employee=optional.get();
        }
        else{
            throw new RuntimeException("No employee with the id "+id+"exist.");
        }
        return employee;
    }
    public void deleteEmployeeById(long id){
        this.employeeRepository.deleteById(id);
    }
    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> getAllEmployeesUsingJPAQL(){
        return employeeRepository.getAllEmployeesUsingJPAQL();
    }

    @Override
    public List<EmployeeProjectReport> getAllEmployeesNameTitleUsingJPAQL(){
        return employeeRepository.getAllEmployeesNameTitleUsingJPAQL();
    }

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        EmployeeReg employeeReg = new EmployeeReg(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword())
        );

        employeeRepo.save(employeeReg);
        return employeeReg.getEmployeename();
    }

    @Override
    public LoginMesage loginEmployee(LoginDTO loginDTO) {
        String msg = "";
        EmployeeReg employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<EmployeeReg> employeeReg = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employeeReg.isPresent()) {
                    return new LoginMesage("Login Success", true);
                } else {
                    return new LoginMesage("Login Failed", false);
                }
            } else {
                return new LoginMesage("password Not Match", false);
            }
        }else {
            return new LoginMesage("Email not exits", false);
        }
    }
}
