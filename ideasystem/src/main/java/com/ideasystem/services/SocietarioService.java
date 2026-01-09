package com.ideasystem.services;

import com.ideasystem.entities.Societario;
import com.ideasystem.repositories.SocietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocietarioService {

    @Autowired
    private SocietarioRepository societarioRepository;

    public Societario save(Societario societario){
        societario.setId(null);
        return societarioRepository.save(societario);
    }
}
