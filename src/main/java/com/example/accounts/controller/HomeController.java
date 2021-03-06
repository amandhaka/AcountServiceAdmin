package com.example.accounts.controller;


import com.example.accounts.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private AdminService adminService;


    @CrossOrigin
    @GetMapping("/")
    public String welcome() {
        return "You are logged in";
    }

}
