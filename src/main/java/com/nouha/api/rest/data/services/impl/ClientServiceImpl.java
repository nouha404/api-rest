package com.nouha.api.rest.data.services.impl;


import com.nouha.api.rest.controllers.dto.request.CreateClientRequestDto;
import com.nouha.api.rest.data.entities.Client;
import com.nouha.api.rest.data.repositories.ClientRepository;
import com.nouha.api.rest.data.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    @Override
    public Page<Client> getAllClientsActive(Pageable pageable) {
        return clientRepository.findAllByActiveTrue(pageable);
    }

    @Override
    public Page<Client> getClientsWithPaginateAndFilter(Pageable pageable, String telephone) {
        System.out.println(clientRepository.findAllByTelephoneContainsAndActiveTrue(pageable,telephone));
        return clientRepository.findAllByTelephoneContainsAndActiveTrue(pageable,telephone);

    }

    @Override
    public List<Client> getClientById(Long id) {
        return clientRepository.findClientById(id);
    }


    @Override
    public void addClient(CreateClientRequestDto dto) {
        Client transformToEntity = dto.TransformToEntity();
        transformToEntity.setActive(true);
        clientRepository.save(transformToEntity);
    }





}
