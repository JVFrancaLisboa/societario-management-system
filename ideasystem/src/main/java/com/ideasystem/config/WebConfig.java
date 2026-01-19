package com.ideasystem.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

@ControllerAdvice
public class WebConfig {

    // Conversor de moeda
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(BigDecimal.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty()) {
                    setValue(null);
                    return;
                }

                try {
                    // Limpeza técnica para evitar o erro de escala (ex: 500 virar 50000)
                    String formatado = text.trim();

                    // Se houver vírgula, tratamos como decimal brasileiro
                    if (formatado.contains(",")) {
                        formatado = formatado.replace(".", "").replace(",", ".");
                    }

                    setValue(new BigDecimal(formatado));
                } catch (Exception e) {
                    setValue(BigDecimal.ZERO);
                }
            }
        });
    }

}
