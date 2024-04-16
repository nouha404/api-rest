package com.nouha.api.rest.controllers.impl;

import com.nouha.api.rest.controllers.ClientRestController;
import com.nouha.api.rest.controllers.dto.request.CreateClientRequestDto;
import com.nouha.api.rest.controllers.dto.response.ClientShowEntityResponseDto;
import com.nouha.api.rest.controllers.dto.response.RestResponseDto;
import com.nouha.api.rest.data.entities.Client;
import com.nouha.api.rest.data.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClientRestControllerImpl implements ClientRestController {
    private final ClientService clientService;
    @Override
    public ResponseEntity<Map<Object,Object>> listerClient(int page, int size, String telephone) {

        Page<Client> clients = clientService.getClientsWithPaginateAndFilter(PageRequest.of(page,size),telephone);
        //transformer objet type client => clientDto
        Page<ClientShowEntityResponseDto> clientsDto = clients.map(ClientShowEntityResponseDto::toDto);

        /*
        Map<Object,Object> model = new HashMap<Object,Object>();
        model.put("results",clientsDto.getContent());
        model.put("telephone", telephone);
        model.put("pages",new int[clientsDto.getTotalPages()]);
        model.put("currentPage",clients.getNumber());
        model.put("PreviousPage", page > 0 ? page-1:page);
        model.put("nextPage", page < clients.getTotalPages() - 1 ? page+1:page);

        model.put("totalItems",clientsDto.getTotalElements());
        model.put("totalPages",clientsDto.getTotalPages());
        Ou je creer un responseDto reutilisable*/

        Map<Object,Object> response = RestResponseDto.response(
                clientsDto.getContent(),
                new int[clientsDto.getTotalPages()],
                clients.getNumber(),
                page > 0 ? page-1:page,
                page < clients.getTotalPages() - 1 ? page+1:page,
                clientsDto.getTotalElements(),
                clientsDto.getTotalPages(),
                HttpStatus.OK
                );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object,Object>> saveClient(
            CreateClientRequestDto clientDto,
            BindingResult bindingResult) {
        Map<Object, Object> response;
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

            response = RestResponseDto.response(errors, HttpStatus.NOT_FOUND);

        } else {
            CreateClientRequestDto createClientRequestDto = clientService.addClient(clientDto);
            response = RestResponseDto.response(createClientRequestDto,HttpStatus.CREATED); //201
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
