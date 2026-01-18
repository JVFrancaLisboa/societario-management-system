package com.ideasystem.entities.enums.societario;

import lombok.Getter;

@Getter
public enum Honorario {
    A_RECEBER("A Receber"),
    RECEBIDO("Recebido");

    private final String descricao;

    Honorario(String descricao) {
        this.descricao = descricao;
    }
}