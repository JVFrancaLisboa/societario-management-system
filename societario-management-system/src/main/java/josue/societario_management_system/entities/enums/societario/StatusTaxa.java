package josue.societario_management_system.entities.enums.societario;

import lombok.Getter;

@Getter
public enum StatusTaxa {
    EMITIR("Emitir"),
    PAGA("Paga"),
    SEM_TAXA("Sem Taxa");

    private final String descricao;

    StatusTaxa(String descricao) {
        this.descricao = descricao;
    }
}