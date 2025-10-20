package com.ideasystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashController {

    @RequestMapping("/")
    public String dashboard(){
        return "screens/dashboard";
    }
}
