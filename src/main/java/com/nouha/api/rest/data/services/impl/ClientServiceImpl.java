package com.nouha.api.rest.data.services.impl;


import com.nouha.api.rest.controllers.dto.request.CreateClientRequestDto;
import com.nouha.api.rest.data.entities.Client;
import com.nouha.api.rest.data.repositories.ClientRepository;
import com.nouha.api.rest.data.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

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
    public CreateClientRequestDto addClient(CreateClientRequestDto dto) {
        Client transformToEntity = dto.TransformToEntity();
        //uui generer une chaine
        UUID uuid = UUID.randomUUID();
        transformToEntity.setUsername(uuid.toString());
        transformToEntity.setPassword(passwordEncoder.encode("nouha12b"));

        transformToEntity.setActive(true);
        clientRepository.save(transformToEntity);

        return CreateClientRequestDto.toDto(transformToEntity); // le convertir en dto
    }

    @Override
    public Client getClientByTelephone(String tel) {
        return clientRepository.findClientByTelephoneAndActiveTrue(tel);
    }


}
