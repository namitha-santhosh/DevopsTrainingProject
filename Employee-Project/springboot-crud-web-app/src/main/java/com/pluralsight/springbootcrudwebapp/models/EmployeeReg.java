package com.pluralsight.springbootcrudwebapp.models;

import jakarta.persistence.*;

@Entity
@Table(name="employeereg")
public class EmployeeReg {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeid;
    @Column(name = "employee_name")
    private String employeename;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public EmployeeReg() {
    }

    public EmployeeReg(int employeeid, String employeename, String email, String password) {
        this.employeeid = employeeid;
        this.employeename = employeename;
        this.email = email;
        this.password = password;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
