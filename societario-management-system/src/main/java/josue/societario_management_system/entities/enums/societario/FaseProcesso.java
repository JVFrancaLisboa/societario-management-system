package josue.societario_management_system.entities.enums.societario;

import lombok.Getter;

@Getter
public enum FaseProcesso {
    VIABILIDADE("Viabilidade"),
    ANALISE("Análise"),
    FINALIZADO("Finalizado");

    private final String descricao;

    FaseProcesso(String descricao) {
        this.descricao = descricao;
    }
}