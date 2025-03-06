package com.nd_monitoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Log4j2
public class MainController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String home() {
        return "main";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
