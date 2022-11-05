package com.neusoft.service;

import com.neusoft.model.Student;

public interface StudentService {
    //注册学生
    int addStudent(Student student);
    //查询学生
    Student queryStudent(Integer id);

}
