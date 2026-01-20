package com.ideasystem.controllers;

import com.ideasystem.entities.Societario;
import com.ideasystem.services.SocietarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/societario")
public class SocietarioController {

    @Autowired
    private SocietarioService societarioService;

    @GetMapping("/dash")
    public String getDashboardView(Model model) {
        // Dados Operacionais
        model.addAttribute("processos", societarioService.findAll());

        // Dados da Sidebar
        model.addAttribute("countAbertura", societarioService.countAberturas());
        model.addAttribute("countAlteracao", societarioService.countAlteracoes());
        model.addAttribute("countBaixa", societarioService.countBaixas());
        model.addAttribute("countFinalizados", societarioService.countFinalizados());

        // Dados Financeiros Dinâmicos
        model.addAttribute("faturamentoMensal", societarioService.getDadosFaturamentoUltimos6Meses());

        // No SocietarioController.java
        model.addAttribute("faturamentoMensal", societarioService.getDadosFaturamentoUltimos6Meses());
        model.addAttribute("mesesLabels", societarioService.getLabelsUltimos6Meses());

        return "screens/departments/dp-societario/dash";
    }

    @GetMapping("/cadastro")
    public String getCadastroView(Model model) {
        if (!model.containsAttribute("societario")) {
            model.addAttribute("societario", new Societario());
        }
        return "screens/departments/dp-societario/cadastro";
    }

    /**
     * Endpoint de Edição: Busca o processo e reutiliza a view de cadastro.
     */
    @GetMapping("/editar/{id}")
    public String getEditView(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        try {
            Societario societario = societarioService.findById(id);
            model.addAttribute("societario", societario);
            return "screens/departments/dp-societario/cadastro";
        } catch (Exception e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao localizar processo: " + e.getMessage());
            return "redirect:/societario/dash";
        }
    }

    @PostMapping("/cadastro")
    public String postCadastro(@Valid @ModelAttribute("societario") Societario societario,
                               BindingResult result,
                               RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return "screens/departments/dp-societario/cadastro";
        }

        try {
            societarioService.save(societario);
            // Mensagem dinâmica: diferencia novo cadastro de atualização
            String acao = (societario.getId() == null) ? "cadastrado" : "atualizado";
            attributes.addFlashAttribute("mensagemSucesso", "Processo " + acao + " com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao processar requisição: " + e.getMessage());
            return "redirect:/societario/cadastro";
        }

        return "redirect:/societario/dash";
    }

    @GetMapping("/excluir/{id}")
    public String deleteProcesso(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            societarioService.deleteById(id);
            attributes.addFlashAttribute("mensagemSucesso", "Processo removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("mensagemErro", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/societario/dash";
    }
}