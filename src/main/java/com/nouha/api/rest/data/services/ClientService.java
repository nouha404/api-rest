package com.nouha.api.rest.data.services;

import com.nouha.api.rest.controllers.dto.request.CreateClientRequestDto;
import com.nouha.api.rest.data.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    Page<Client> getAllClientsActive(Pageable pageable);
    Page<Client> getClientsWithPaginateAndFilter(Pageable pageable,String telephone);
    List<Client> getClientById(Long id);

    CreateClientRequestDto addClient(CreateClientRequestDto dto);

    Client getClientByTelephone(String tel);

}
