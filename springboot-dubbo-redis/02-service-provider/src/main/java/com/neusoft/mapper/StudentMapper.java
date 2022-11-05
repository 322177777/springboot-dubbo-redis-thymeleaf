package com.neusoft.mapper;

import com.neusoft.model.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    //查询phone
    int selectPhone(@Param("phone") String phone);
    //添加学生
    int insertStudent(Student student);
    //查询学生
    Student selectStudent(Integer id);
}
