package com.ideasystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dp")
public class DpController {

    @GetMapping("/fiscal")
    public String dashboard(){
        return "/screens/departments/dp-fiscal";
    }
}
