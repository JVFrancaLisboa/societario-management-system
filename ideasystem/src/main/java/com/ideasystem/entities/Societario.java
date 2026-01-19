package com.ideasystem.entities;

import com.ideasystem.entities.enums.societario.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "processos_societarios")
public class Societario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do processo é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String nomeProcesso;

    @NotNull(message = "O tipo do processo deve ser selecionado.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoProcesso tipo;

    @NotNull(message = "A fase do processo deve ser selecionada.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private FaseProcesso fase;

    @NotNull(message = "O status da taxa é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusTaxa statusTaxa;

    @NotBlank(message = "O contato responsável é obrigatório.")
    @Column(nullable = false, length = 150)
    private String contatoResponsavel;

    @NotNull(message = "O status do honorário é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(name = "status_honorario", nullable = false, length = 20)
    private Honorario valorHonorario;

    @NotNull(message = "O valor total é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @NotBlank(message = "O celular é obrigatório.")
    @Column(length = 20)
    private String celular;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Insira um endereço de e-mail válido.")
    @Column(nullable = false, length = 200)
    private String email;

    @Column(name = "data_criacao")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao;

    @NotBlank(message = "O CNPJ/CPF é obrigatório.")
    // Excelência: Regex flexível que aceita com ou sem máscara
    @Pattern(regexp = "(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})|(\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2})",
            message = "Formato de documento inválido.")
    @Column(nullable = false, length = 18)
    private String cnpjCpf;

    // Se o usuário não informar a data (migração), o sistema usa a data atual
    @PrePersist
    protected void onCreate() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDate.now();
        }
    }

    // Setter Inteligente: Remove a máscara para o banco de dados ficar limpo (Apenas números)
    public void setCnpjCpf(String cnpjCpf) {
        if (cnpjCpf != null) {
            this.cnpjCpf = cnpjCpf.replaceAll("\\D", "");
        }
    }

}