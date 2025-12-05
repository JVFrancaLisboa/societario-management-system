package com.ideasystem.controllers;

import com.ideasystem.entities.Societario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/societario")
public class SocietarioController {

    @GetMapping("/dash")
    public String getView(Model model){
        //model.addAttribute("societario", new Societario());
        return "screens/departments/dp-societario";
    }
}
