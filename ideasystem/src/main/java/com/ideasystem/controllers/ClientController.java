package com.ideasystem.controllers;

import com.ideasystem.entities.ClientEntity;
import com.ideasystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public String allClients(Model model){
        model.addAttribute("clients", clientService.allClients());
        return "screens/clients/all";
    }

    @GetMapping("/form")
    public String clientForm(Model model){
        model.addAttribute("client", new ClientEntity());
        return "screens/clients/form";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model){
        model.addAttribute("client", new ClientEntity());
        return "screens/clients/cadastro";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute ClientEntity client){
        clientService.saveCliente(client);
        return "screens/clients/all";
    }
}
