package com.restTemplate.managerControl.managerControl.models;

public class ProjectRequest {
    private Long id;
    private String title;
    private Long managerId;

    public ProjectRequest(){}

    public ProjectRequest(Long id, String title, Long managerId) {
        this.id = id;
        this.title = title;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
