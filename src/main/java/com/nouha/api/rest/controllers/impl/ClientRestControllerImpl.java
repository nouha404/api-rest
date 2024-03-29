package com.nouha.api.rest.controllers.impl;

import com.nouha.api.rest.controllers.ClientRestController;
import com.nouha.api.rest.controllers.dto.request.CreateClientRequestDto;
import com.nouha.api.rest.controllers.dto.response.ClientShowEntityResponseDto;
import com.nouha.api.rest.data.entities.Client;
import com.nouha.api.rest.data.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ClientRestControllerImpl implements ClientRestController {
    private final ClientService clientService;
    @Override
    public ResponseEntity<Map<Object,Object>> listerClient(int page, int size, String telephone) {

        Page<Client> clients = clientService.getClientsWithPaginateAndFilter(PageRequest.of(page,size),telephone);
        //transformer objet type client => clientDto
        Page<ClientShowEntityResponseDto> clientsDto = clients.map(ClientShowEntityResponseDto::toDto);

        Map<Object,Object> model = new HashMap<Object,Object>();
        model.put("results",clientsDto.getContent());
        model.put("telephone", telephone);
        model.put("pages",new int[clientsDto.getTotalPages()]);
        model.put("currentPage",clients.getNumber());
        model.put("PreviousPage", page > 0 ? page-1:page);
        model.put("nextPage", page < clients.getTotalPages() - 1 ? page+1:page);

        model.put("totalItems",clientsDto.getTotalElements());
        model.put("totalPages",clientsDto.getTotalPages());
        
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object,Object>> saveClient(CreateClientRequestDto clientDto) {
        return null;
    }
}
