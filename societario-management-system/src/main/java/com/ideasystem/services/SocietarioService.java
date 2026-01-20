package com.ideasystem.services;

import com.ideasystem.entities.Societario;
import com.ideasystem.entities.enums.societario.FaseProcesso;
import com.ideasystem.entities.enums.societario.TipoProcesso;
import com.ideasystem.repositories.SocietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SocietarioService {

    @Autowired
    private SocietarioRepository societarioRepository;

    @Transactional(readOnly = true)
    public List<Societario> findAll() {
        return societarioRepository.findAllByOrderByIdDesc();
    }

    // Métodos para alimentar a Sidebar
    public long countAberturas() { return societarioRepository.countByTipo(TipoProcesso.ABERTURA); }
    public long countAlteracoes() { return societarioRepository.countByTipo(TipoProcesso.ALTERACAO); }
    public long countBaixas() { return societarioRepository.countByTipo(TipoProcesso.BAIXA); }
    public long countFinalizados() { return societarioRepository.countByFase(FaseProcesso.FINALIZADO); }

    @Transactional
    public Societario save(Societario societario) {
        return societarioRepository.save(societario);
    }

    @Transactional(readOnly = true)
    public Societario findById(Long id) {
        return societarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado."));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!societarioRepository.existsById(id)) {
            throw new RuntimeException("Não foi possível excluir: Processo não encontrado.");
        }
        societarioRepository.deleteById(id);
    }

    /**
     * Calcula o faturamento total bruto de todos os processos.
     * Retorna ZERO caso não haja registros.
     */
    @Transactional(readOnly = true)
    public BigDecimal getTotalFaturamento() {
        BigDecimal total = societarioRepository.sumTotalFaturamento();
        return (total != null) ? total : BigDecimal.ZERO;
    }

    public List<BigDecimal> getDadosFaturamentoUltimos6Meses() {
        List<BigDecimal> faturamento = new ArrayList<>();
        LocalDate hoje = LocalDate.now();

        for (int i = 5; i >= 0; i--) {
            LocalDate dataAlvo = hoje.minusMonths(i);
            BigDecimal soma = societarioRepository.sumByMesEAno(
                    dataAlvo.getMonthValue(),
                    dataAlvo.getYear()
            );
            faturamento.add(soma != null ? soma : BigDecimal.ZERO);
        }
        return faturamento;
    }

    public List<String> getLabelsUltimos6Meses() {
        List<String> labels = new ArrayList<>();
        LocalDate agora = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", new Locale("pt", "BR"));

        for (int i = 5; i >= 0; i--) {
            labels.add(agora.minusMonths(i).format(formatter).toUpperCase());
        }
        return labels;
    }
}