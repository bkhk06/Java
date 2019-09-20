package com.adcc.springbootconfigdemo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Liu.DA on 2019/9/20
 */
@RestController
public class Controller {
    @Value("${salary}")
    private String salary;
    @RequestMapping("/salary")
    public String getSalary(){
        System.err.println("Salary is: "+salary);
        return salary;
    }

    @Value("${content}")
    private String content;
    @RequestMapping("/content")
    public String getContent(){
        System.err.println("Content is: "+content);
        return content;
    }
}
