package com.student.demo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import java.util.Optional;

@RestController

public class StudentController {

    @Autowired
    private DBUtils dbUtils;
    /**
     * 获得所有的学生的api 接口：
     * @return 返回结果列表
     */
    @GetMapping(value = "/students")
    public List getStudentForms(){
        return dbUtils.findAll();
    }

    /**
     * 根据 account 来获得 一个学生的信息：
     * @param account  学号信息
     * @return 返回查询的结果；
     */
    @GetMapping(value = "/students/{account}")
    public Object getStudentFormById(@PathVariable("account") String account){
        StudentForm student=dbUtils.findByAccount(account);
        if(student!=null)
            return student;
        else{
            return "查找失败：当前学号的学生没有！";
        }
    }

    /**
     * 增加一个新的学生信息到数据库：
     * @param studentForm 前端传过来的参数
     * @param bindingResult  可以理解为异常捕获类
     * @return
     */
    @PostMapping(value = "/students/add")
    public Object addStudentForm(@Valid StudentForm studentForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return "不符合条件，插入失败，请检查是不是有正确的请求参数";
        }
        dbUtils.save(studentForm);
        return studentForm;
    }

    /**
     * 根据Id 来更新一个学生的信息
     * @param id  学生id 号码（必选）
     * @param age  年龄（可选）
     * @param name  姓名（可选）
     * @param password  密码（可选）
     * @param account  账号（可选）
     * @return
     */
    @PostMapping(value = "/students/update")
    public Object updateStudentFormById(@RequestParam("id") Integer id,
                                        @RequestParam(value = "age",required = false,defaultValue = "-1") Integer age,
                                        @RequestParam(value = "name",required = false,defaultValue = "null") String name,
                                        @RequestParam(value = "password",required = false,defaultValue = "null") String password,
                                        @RequestParam(value = "account",required = false,defaultValue = "null") String account){
        Optional studentC=dbUtils.findById(id);
        System.out.println("StudengC:"+studentC);
        if(studentC.isPresent()){
            StudentForm student=(StudentForm) studentC.get();
            if(age!=-1){
                student.setAge(age);
            }
            if(!name.equals("null")){
                student.setName(name);
            }
            if(!account.equals("null")){
                student.setAccount(account);
            }
            if(!password.equals("null")){
                student.setPassword(password);
            }
            dbUtils.save(student);
            return  student;
        }
        return  "修改失败，没有找到学生信息！";
    }

    /**
     * 删除指定ID的学生信息
     * @param id
     * @return
     */
    @PostMapping(value ="/students/delete")
    public String deleteById(@RequestParam(value = "id") Integer id){
        Optional studentC=dbUtils.findById(id);
        if(studentC.isPresent()){
            StudentForm student=(StudentForm) studentC.get();
            dbUtils.deleteById(id);
            return "{'msg':'删除成功','object':'"+student.toString()+"'}";
        }
        return "没有办法删除：找不到要删除的信息！";
    }

}