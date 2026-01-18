/**
 * cadastro.js - Versão de Excelência para IDEA Cont
 * Integração total: Validação Client-side (JS) + Server-side (Java/Thymeleaf)
 */
document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const submitBtn = document.getElementById("btn-salvar");

    // Definição dos campos para validação em tempo real (on-the-fly)
    const REQUIRED_FIELDS = [
        { id: "processoName", label: "Processo", validate: v => v.trim().length > 0 },
        { 
            id: "cnpjCpf", label: "CNPJ/CPF", 
            validate: v => {
                const clean = v.replace(/\D/g, '');
                // Uso das bibliotecas oficiais carregadas no HTML
                if (clean.length === 11) return typeof CPF !== 'undefined' ? CPF.isValid(clean) : true;
                if (clean.length === 14) return typeof CNPJ !== 'undefined' ? CNPJ.isValid(clean) : true;
                return false;
            }
        },
        { id: "tipo", label: "Tipo", validate: v => v !== "" },
        { id: "fase", label: "Fase", validate: v => v !== "" },
        { id: "statusTaxa", label: "Taxa", validate: v => v !== "" },
        { id: "contatoResponsavel", label: "Contato", validate: v => v.trim().length > 0 },
        { id: "valorHonorario", label: "Honorário", validate: v => v !== "" },
        { id: "valorTotal", label: "Valor", validate: v => v.trim().length > 0 },
        { id: "celular", label: "Celular", validate: v => v.replace(/\D/g, '').length >= 10 },
        { id: "email", label: "E-mail", validate: v => /^\S+@\S+\.\S+$/.test(v) }
    ];

    /**
     * Valida um campo individualmente e aplica as classes do Bootstrap.
     * Esta função é inteligente: ela limpa os erros vindos do Java (Thymeleaf) 
     * assim que o usuário torna o campo válido.
     */
    function validateField(config) {
        const input = document.getElementById(config.id);
        if (!input) return true;

        const isValid = config.validate(input.value);
        
        // Alterna classes conforme a validade
        if (isValid) {
            input.classList.remove("is-invalid");
            input.classList.add("is-valid");
            // Limpa mensagens de erro (inclusive as injetadas pelo Java)
            const feedback = input.nextElementSibling;
            if (feedback && feedback.classList.contains("invalid-feedback")) {
                feedback.innerHTML = "";
            }
        } else {
            input.classList.remove("is-valid");
            input.classList.add("is-invalid");
            const feedback = input.nextElementSibling;
            if (feedback && feedback.classList.contains("invalid-feedback")) {
                feedback.innerHTML = `O campo <strong>${config.label}</strong> é inválido.`;
            }
        }
        return isValid;
    }

    // ────────────────────────────────────────────────
    // Configuração de Máscaras (JQuery Mask)
    // ────────────────────────────────────────────────
    if (window.jQuery) {
        const $ = window.jQuery;
        const options = {
            onKeyPress: function(val, e, field, options) {
                const masks = ['000.000.000-00##', '00.000.000/0000-00'];
                const mask = (val.replace(/\D/g, '').length > 11) ? masks[1] : masks[0];
                $('#cnpjCpf').mask(mask, options);
            }
        };
        $('#cnpjCpf').mask('000.000.000-00##', options);
        $('#celular').mask('(00) 00000-0000');
        $('#valorTotal').mask("#.##0,00", {reverse: true});
    }

    // ────────────────────────────────────────────────
    // Listeners para Validação Dinâmica
    // ────────────────────────────────────────────────
    REQUIRED_FIELDS.forEach(config => {
        const input = document.getElementById(config.id);
        if (input) {
            // Se o campo já veio com erro do Java (Thymeleaf), 
            // validamos assim que o usuário começar a interagir.
            input.addEventListener("input", () => {
                if (input.classList.contains("is-invalid") || input.value.length > 0) {
                    validateField(config);
                }
            });

            if (input.tagName === "SELECT") {
                input.addEventListener("change", () => validateField(config));
            }
        }
    });

    // ────────────────────────────────────────────────
    // Evento Final de Submissão
    // ────────────────────────────────────────────────
    form.addEventListener("submit", (e) => {
        // Bloqueia o envio para realizar a última checagem client-side
        e.preventDefault(); 
        let allValid = true;
        let firstError = null;

        REQUIRED_FIELDS.forEach(config => {
            if (!validateField(config)) {
                allValid = false;
                if (!firstError) firstError = document.getElementById(config.id);
            }
        });

        if (allValid) {
            // Desabilita o botão para evitar cliques duplos (Double Submit)
            submitBtn.disabled = true;
            submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm"></span> Enviando...';
            
            // Envia o formulário para o Controller Java
            form.submit(); 
        } else {
            // Foco e Scroll suave até o primeiro erro encontrado
            if (firstError) {
                firstError.focus();
                firstError.scrollIntoView({ behavior: 'smooth', block: 'center' });
            }
        }
    });
});