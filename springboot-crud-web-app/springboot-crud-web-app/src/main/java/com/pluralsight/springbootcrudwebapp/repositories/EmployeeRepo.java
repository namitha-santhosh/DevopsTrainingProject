package com.pluralsight.springbootcrudwebapp.repositories;

import com.pluralsight.springbootcrudwebapp.models.EmployeeReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeReg,Integer>
{
    Optional<EmployeeReg> findOneByEmailAndPassword(String email, String password);
    EmployeeReg findByEmail(String email);
}