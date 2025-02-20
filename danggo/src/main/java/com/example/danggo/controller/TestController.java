package com.example.danggo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public String test() throws Exception {
        return "리액트-스프링부트 연동";
    }

}
