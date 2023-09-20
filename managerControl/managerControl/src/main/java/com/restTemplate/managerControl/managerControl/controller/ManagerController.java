package com.restTemplate.managerControl.managerControl.controller;

import com.restTemplate.managerControl.managerControl.models.Manager;
import com.restTemplate.managerControl.managerControl.models.ProjectRequest;
import com.restTemplate.managerControl.managerControl.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerController {
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/showManagers")
    public @ResponseBody List<Manager> getManagers(){
        List<Manager> managers=managerRepository.findAll();
        return managers;
    }
    @GetMapping("/findManager/{managerId}")
    public @ResponseBody List<Manager> findManagerById(@PathVariable Long managerId){
        List<Manager> manager= managerRepository.findByManagerId(managerId);
        System.out.println(manager);
        return manager;
    }
    @PostMapping("/saveManager")
    public Manager saveManager(@RequestBody Manager manager){
        List<Manager> manager1=managerRepository.findByManagerId(manager.getManagerId());
        if(manager1.isEmpty()){
            return managerRepository.save(manager);
            //return ResponseEntity.ok("Manager with id "+manager.getManagerId()+" added Successfully.");
        }
        return manager;
    }

    @GetMapping("/showAllProjectsManagedBy/{id}")
    public ResponseEntity<ProjectRequest> getProjects(@PathVariable Long managerId) {

        ResponseEntity<List<ProjectRequest>> projectResponse = restTemplate.exchange(
                "http://localhost:8081/api/v1/managers/findManager/" + managerId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProjectRequest>>() {
                }
        );

        List<ProjectRequest> projects = projectResponse.getBody();

        if (projects != null && !projects.isEmpty()) {
            // Assuming you want the first element of the array
            ProjectRequest manager = projects.get(0);
            return ResponseEntity.ok(manager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
