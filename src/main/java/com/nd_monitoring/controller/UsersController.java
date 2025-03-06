package com.nd_monitoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Log4j2
public class UsersController {
    @GetMapping("/userslist")
    public String userslist() {
        return "users/userslist";
    }

    @GetMapping("/createUser")
    public String createUser(){
        return "users/createUser";
    }

}
