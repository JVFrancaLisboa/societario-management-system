package com.ideasystem.controllers;

import com.ideasystem.entities.Societario;
import com.ideasystem.services.SocietarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/societario")
public class SocietarioController {

    @Autowired
    private SocietarioService societarioService;

    @GetMapping("/dash")
    public String getDashboardView(Model model) {
        return "screens/departments/dp-societario/dash";
    }

    @GetMapping("/cadastro")
    public String getCadastroView(Model model) {

        if (!model.containsAttribute("societario")) {
            model.addAttribute("societario", new Societario());
        }
        return "screens/departments/dp-societario/cadastro";
    }

    @PostMapping("/cadastro")
    public String postCadastro(@Valid @ModelAttribute("societario") Societario societario,
                               BindingResult result,
                               RedirectAttributes attributes) {

        // 1. Verificação de Integridade (Bean Validation)
        if (result.hasErrors()) {
            // Se houver erro (ex: CPF inválido barrado pelo Java),
            // retornamos para a tela de cadastro sem redirecionar,
            // mantendo os dados preenchidos e exibindo os erros.
            return "screens/departments/dp-societario/cadastro";
        }

        // 2. Persistência de Dados
        try {
            societarioService.save(societario);
            // 3. Feedback de Sucesso para o Usuário
            attributes.addFlashAttribute("mensagemSucesso", "Processo cadastrado com sucesso!");
        } catch (Exception e) {
            // Tratamento de exceções inesperadas do banco de dados
            attributes.addFlashAttribute("mensagemErro", "Erro ao salvar o processo: " + e.getMessage());
            return "redirect:/societario/cadastro";
        }

        return "redirect:/societario/dash";
    }
}