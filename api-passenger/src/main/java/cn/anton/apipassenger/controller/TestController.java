package cn.anton.apipassenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author: Anton
 * @create_date: 2023/9/5 14:27
 */
@RestController
public class TestController {
    
    @RequestMapping("/test")
    public String testSuccess(){
        return "testSuccess";
    }
    
}
