package com.ideasystem.services;

import com.ideasystem.entities.ClientEntity;
import com.ideasystem.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientEntity> allClients() {

        if (clientRepository.findAll().isEmpty()) return null;

        return clientRepository.findAll();
    }

    public ClientEntity saveCliente(ClientEntity client){
        client.setId(null);
        return clientRepository.save(client);
    }

    public BigDecimal converterHonorario(String honorarioTemp){
        return null;
    }

}
