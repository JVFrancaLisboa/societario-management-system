package josue.societario_management_system.entities.enums.societario;

import lombok.Getter;

@Getter
public enum TipoProcesso {
    ABERTURA("Abertura"),
    ALTERACAO("Alteração"),
    TRANSFORMACAO("Transformação"),
    BAIXA("Baixa"),
    PARALIZACAO("Paralisação"),
    REATIVACAO("Reativação"),
    IMUNIDADE("Imunidade");

    private final String descricao;

    TipoProcesso(String descricao) {
        this.descricao = descricao;
    }
}