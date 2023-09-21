package com.pluralsight.springbootcrudwebapp.controller;

import com.pluralsight.springbootcrudwebapp.models.Employee;
import com.pluralsight.springbootcrudwebapp.models.Position;
import com.pluralsight.springbootcrudwebapp.models.Skill;
import com.pluralsight.springbootcrudwebapp.repositories.PositionRepository;
import com.pluralsight.springbootcrudwebapp.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping
    @ResponseBody
    public List<Position> listOfAllPromotion()
    {
        return positionRepository.findAll();
    }

    @PostMapping
    public void add(@RequestBody Position position)
    {
        if(position.getPosition().equals("trainee")||position.getPosition().equals("manager")||position.getPosition().equals("employee"))
            positionRepository.saveAndFlush(position);
        else
            System.out.println("Not expected position entered");
    }

    @GetMapping("/{position}")
    @ResponseBody
    public String salary(@PathVariable String position){
        String salary;
        switch (position) {
            case "trainee":
                salary = "10000 Rs";
                break;
            case "manager":
                salary = "100000 Rs";
                break;
            case "employee":
                salary = "50000 Rs";
                break;
            default:
                return "Position not found";
        }
        return "Salary for " + position + " is " + salary;
    }

    @PostMapping("/skill")
    public void createEmployee(@RequestBody Position position) {
        if(position.getPosition().equals("trainee")||position.getPosition().equals("manager")||position.getPosition().equals("employee"))
            positionRepository.save(position);
        else
            System.out.println("Not expected position entered");
    }

    @GetMapping("/skill/{id}")
    public List<Skill> getSkillsForEmployee(@PathVariable Long id) {
        Position position = positionRepository.findById(id).orElse(null);
        return position != null ? position.getSkills() : null;
    }

}