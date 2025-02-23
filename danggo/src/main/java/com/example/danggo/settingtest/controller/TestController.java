package com.example.danggo.settingtest.controller;

import com.example.danggo.settingtest.dao.StestDAO;
import com.example.danggo.settingtest.service.StestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private StestService stestService;

    @PostMapping("/test")
    public String test() throws Exception {
        return "리액트-스프링부트 연동";
    }

    @PostMapping("/mybatis/test")
    public String mybatisTest(@RequestParam String note) throws Exception {
        stestService.insertTest(note);
        return "마이바티스 연동";
    }

}
