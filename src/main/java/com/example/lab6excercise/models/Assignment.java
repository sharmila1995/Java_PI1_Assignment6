package com.example.lab6excercise.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Assignment {
    private String name;
    private double grade;
    private LocalDate assignmentDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public LocalDate getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDate assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
