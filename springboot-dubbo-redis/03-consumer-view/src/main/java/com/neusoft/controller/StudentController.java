package com.neusoft.controller;

import com.neusoft.model.ReturnObject;
import com.neusoft.model.Student;
import com.neusoft.service.StudentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class StudentController {
    @DubboReference(version = "1.0")
    private StudentService studentService;


    @PostMapping("/student.do")
    @ResponseBody
    public Object insertStudent(Student student){
        ReturnObject returnObject = new ReturnObject();
        int ret = studentService.addStudent(student);
        if(ret == 1){
            returnObject.setCode("1");
        }else if (ret == 2){
            returnObject.setCode("0");
            returnObject.setMessage("手机号已注册");
        }else {
            returnObject.setCode("0");
            returnObject.setMessage("系统繁忙,请稍后重试...");
        }
        return returnObject;
    }

    @GetMapping("/queryStudent.do")
    public String queryStudent(int id , Model model){
        //ReturnObject returnObject = new ReturnObject();
        Student student = studentService.queryStudent(id);
        //returnObject.setMessage(student.toString());
        model.addAttribute("student",student);
        System.out.println(student.toString());
        return "queryStudent";
    }
}
