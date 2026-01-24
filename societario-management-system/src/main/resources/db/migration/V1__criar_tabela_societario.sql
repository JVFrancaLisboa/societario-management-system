CREATE TABLE processos_societarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_processo VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    fase VARCHAR(50) NOT NULL,
    status_taxa VARCHAR(20) NOT NULL,
    contato_responsavel VARCHAR(150) NOT NULL,
    status_honorario VARCHAR(20) NOT NULL,
    valor_total DECIMAL(12,2) NOT NULL,
    celular VARCHAR(20) NOT NULL,
    email VARCHAR(200) NOT NULL,
    cnpj_cpf VARCHAR(18) NOT NULL,
    data_criacao DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;