package com.ideasystem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "certificates")
public class CertificateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = true)
    private ClientEntity cliente;

    @Column
    private String razaoSocial;

    @Column
    private LocalDate dataValidade;

    @Column(length = 20)
    private String contato;

    @Column(length = 20)
    private String whatsapp;

    @Column(length = 200)
    private String email;
}
