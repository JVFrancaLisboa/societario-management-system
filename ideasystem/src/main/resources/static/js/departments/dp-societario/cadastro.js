document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const submitBtn = document.getElementById("btn-salvar");

    const REQUIRED_FIELDS = [
        { id: "processoName", label: "Processo", validate: v => v.trim().length > 0 },
        { 
            id: "cnpjCpf", label: "CNPJ/CPF", 
            validate: v => {
                const clean = v.replace(/\D/g, '');
                if (clean.length === 11) {
                    return typeof CPF !== 'undefined' ? CPF.isValid(clean) : true;
                }
                if (clean.length === 14) {
                    return typeof CNPJ !== 'undefined' ? CNPJ.isValid(clean) : true;
                }
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
        
        input.classList.toggle("is-invalid", !isValid);
        input.classList.toggle("is-valid", isValid);
        
        const feedback = input.nextElementSibling;
        if (feedback && feedback.classList.contains("invalid-feedback")) {
            feedback.innerHTML = isValid ? "" : `O campo <strong>${config.label}</strong> é inválido.`;
        }
        return isValid;
    }

    // Máscaras JQuery
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

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        let allValid = true;

        REQUIRED_FIELDS.forEach(config => {
            if (!validateField(config)) allValid = false;
        });

        if (allValid) {
            submitBtn.disabled = true;
            submitBtn.innerHTML = "Enviando...";
            form.submit();
        } else {
            const firstError = document.querySelector(".is-invalid");
            if (firstError) firstError.focus();
        }
    });
});