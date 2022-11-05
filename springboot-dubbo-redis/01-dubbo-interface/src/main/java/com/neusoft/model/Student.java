package com.neusoft.model;

import java.io.Serial;
import java.io.Serializable;


public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 677780646417400499L;

    private Integer id;
    private String name;
    private String phone;
    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
    public static Student defaultStudent(){
        Student student = new Student();
        student.setId(0);
        student.setName("-");
        student.setPhone("-");
        student.setAge(0);
        return student;
    }
}
