/**
 * cadastro.js - Versão de Excelência para IDEA Cont
 * Foco: Integridade de documentos e valores monetários.
 */
document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const submitBtn = document.getElementById("btn-salvar");

    const REQUIRED_FIELDS = [
        { id: "processoName", label: "Processo", validate: v => v.trim().length > 0 },
        { 
            id: "cnpjCpf", label: "CNPJ/CPF", 
            validate: v => {
                const clean = v.replace(/\D/g, '');
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

    function validateField(config) {
        const input = document.getElementById(config.id);
        if (!input) return true;
        const isValid = config.validate(input.value);
        input.classList.toggle("is-valid", isValid);
        input.classList.toggle("is-invalid", !isValid);
        return isValid;
    }

    if (window.jQuery) {
        const $ = window.jQuery;
        const maskBehavior = val => val.replace(/\D/g, '').length <= 11 ? '000.000.000-00999' : '00.000.000/0000-00';
        const options = { onKeyPress: (val, e, field, opts) => field.mask(maskBehavior(val), opts) };
        
        $('#cnpjCpf').mask(maskBehavior, options);
        $('#celular').mask('(00) 00000-0000');
        $('#valorTotal').mask("#.##0,00", {reverse: true});
    }

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        let allValid = true;
        REQUIRED_FIELDS.forEach(config => { if (!validateField(config)) allValid = false; });

        if (allValid) {
            const valorInput = document.getElementById("valorTotal");
            if (valorInput) {
                // EXCELÊNCIA: Remove apenas pontos de milhar. 
                // Mantém a vírgula para o Java tratar. Ex: "1.500,00" -> "1500,00"
                valorInput.value = valorInput.value.replace(/\./g, '');
            }
            submitBtn.disabled = true;
            submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm"></span> Enviando...';
            form.submit();
        }
    });
});