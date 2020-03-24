package com.example.fastjson;

import lombok.Data;

@Data
public class Student {
    private String studentName;
    private Integer studentAge;

    public Student(String studentName, Integer studentAge) {
        this.studentName = studentName;
        this.studentAge = studentAge;
    }
}
