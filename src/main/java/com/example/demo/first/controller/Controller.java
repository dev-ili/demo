package com.example.demo.first.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/test")
    public String getMethodName(@RequestParam Map<String, Object> param) {
        String result = "";
        System.out.println(param.toString());
        result = "param = " + param.toString();
        return result;
    }

}