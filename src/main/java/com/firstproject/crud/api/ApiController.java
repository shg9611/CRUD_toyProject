package com.firstproject.crud.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }
}
