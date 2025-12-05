package com.ideasystem.entities;

// Importa os Enums criados
import com.ideasystem.entities.enums.TipoProcesso;
import com.ideasystem.entities.enums.FaseProcesso;
import com.ideasystem.entities.enums.StatusTaxa;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Data
@Entity
public class Societario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ... (Código Protocolo)

    // Campo Processo*
    @Column(nullable = false, length = 100)
    private String nomeProcesso;

    // Campo CNPJ
    @Column(length = 14)
    private String cnpjCpf;

    // Campo Tipo* (Agora um Enum)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoProcesso tipo;

    // Campo Fase* (Agora um Enum)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private FaseProcesso fase;

    // Campo Taxa* (Agora um Enum)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusTaxa statusTaxa; // Nome renomeado para refletir o conteúdo Enum

    // Opcional: Valor real da taxa
    @Column
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal valorDaTaxa;

    // Campo Contato*
    @Column(nullable = false, length = 150)
    private String contatoResponsavel;

    // Campo Honorário* (Valor monetário do honorário)
    @Column(nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal valorHonorario;

    // Campo Valor* (Valor total do processo/serviço)
    @Column(nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal valorTotal;

    // Campo Celular*
    @Column(nullable = false, length = 20)
    private String celular;

    // Campo E-mail*
    @Column(nullable = false, length = 200)
    private String email;

    // ... (Metadados)
}