package com.ideasystem.services;

import com.ideasystem.entities.Societario;
import com.ideasystem.repositories.SocietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SocietarioService {

    @Autowired
    private SocietarioRepository societarioRepository;

    /**
     * Salva um novo processo societário.
     * O uso de @Transactional garante a integridade da operação no banco de dados.
     */
    @Transactional
    public Societario save(Societario societario) {
        societario.setId(null);
        return societarioRepository.save(societario);
    }
}