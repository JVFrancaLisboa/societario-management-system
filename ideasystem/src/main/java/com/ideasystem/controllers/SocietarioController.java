package com.ideasystem.controllers;

import com.ideasystem.entities.Societario;
import com.ideasystem.services.SocietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/societario")
public class SocietarioController {

    @Autowired
    private SocietarioService societarioService;

    @GetMapping("/dash")
    public String getDashboardView(Model model){
        //model.addAttribute("societario", new Societario());
        return "screens/departments/dp-societario/dash";
    }

    @GetMapping("/cadastro")
    public String getCadastroView(Model model){
        model.addAttribute("societario", new Societario());
        //model.addAttribute("societario", new Societario());
        return "screens/departments/dp-societario/cadastro";
    }

    @PostMapping("/cadastro")
    public String postCadastro(@ModelAttribute("societario") Societario societario){
        societarioService.save(societario);
        return "redirect:/societario/dash";
    }
}
