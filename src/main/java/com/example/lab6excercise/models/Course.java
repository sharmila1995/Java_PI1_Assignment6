package com.example.lab6excercise.models;

import java.text.DecimalFormat;
import java.util.List;

public class Course {
    private String name;
    private double averageGrade;
    private List<Assignment> assignment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageGrade() {
        double total = 0.0;
        for (int i = 0; i < assignment.size(); i++){
            total =+ assignment.get(i).getGrade();
        }
        averageGrade = Math.round((total/assignment.size()) * 100.0)/100.0;

        return averageGrade;
    }


    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public List<Assignment> getAssignment() {
        return assignment;
    }

    public void setAssignment(List<Assignment> assignment) {
        this.assignment = assignment;
    }
}
