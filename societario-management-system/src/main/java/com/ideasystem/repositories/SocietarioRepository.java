package com.ideasystem.repositories;

import com.ideasystem.entities.Societario;
import com.ideasystem.entities.enums.societario.FaseProcesso;
import com.ideasystem.entities.enums.societario.TipoProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SocietarioRepository extends JpaRepository<Societario, Long> {

    List<Societario> findAllByOrderByIdDesc();

    //Contagens automáticas via Spring Data JPA
    long countByTipo(TipoProcesso tipo);
    long countByFase(FaseProcesso fase);

    @Query("SELECT SUM(s.valorTotal) FROM Societario s")
    BigDecimal sumTotalFaturamento();

    // Busca Faturamento Mensal
    @Query("SELECT SUM(s.valorTotal) FROM Societario s WHERE MONTH(s.dataCriacao) = :mes AND YEAR(s.dataCriacao) = :ano")
    BigDecimal sumByMesEAno(@Param("mes") int mes, @Param("ano") int ano);
}