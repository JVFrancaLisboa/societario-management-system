package com.ideasystem.services;

import com.ideasystem.entities.ClientEntity;
import com.ideasystem.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<ClientEntity> allClients() {

        if (clientRepository.findAll().isEmpty()) return null;

        return clientRepository.findAll();
    }

}
