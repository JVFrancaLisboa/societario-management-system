package com.ideasystem.entities;

import com.ideasystem.entities.enums.*;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String razaoSocial;

    @Column(length = 14)
    private String cnpjCpf;

    @Column(length = 40)
    private String inscricaoEstadual;

    @Column(length = 40)
    private String inscricaoMunicipal;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Tributacao tributacao;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AtivEconomica atividadeEconomica;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusEmpresa statusEmpresa;

    @Column(length = 8)
    private String cep;

    @Column(length = 150)
    private String logradouro;

    @Column(length = 10)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @Column(length = 150)
    private String responsavel;

    @Column(length = 14)
    private String cpf;

    @Column(length = 40)
    private String rgCnh;

    @Column(length = 20)
    private String orgaoEmissor;

    @Column(length = 2)
    private String ufDocumento;

    @Column(length = 20)
    private String telefone;

    @Column(length = 20)
    private String celular;

    @Column(length = 200)
    private String email;

    private BigDecimal valorHonorario;

    private Integer vencimento; // ou LocalDate se for uma data completa

    @Enumerated(EnumType.STRING)
    private Contrato contrato;

    private LocalDate dataContrato;

    @Enumerated(EnumType.STRING)
    private Servico contabilidade;

    @Enumerated(EnumType.STRING)
    private Servico fiscal;

    @Enumerated(EnumType.STRING)
    private Servico folha;

    @Enumerated(EnumType.STRING)
    private Servico financeiro;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}