package com.pluralsight.springbootcrudwebapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "skill_name")
    private String skillName;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    /*@JsonManagedReference
    @JsonIgnore*/
    @JsonBackReference
    private List<Position> project;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public List<Position> getProject() {
        return project;
    }

    public void setProject(List<Position> project) {
        this.project = project;
    }
}
