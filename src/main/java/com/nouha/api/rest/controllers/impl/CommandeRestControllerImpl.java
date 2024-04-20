package com.nouha.api.rest.controllers.impl;

import ch.qos.logback.core.model.Model;
import com.nouha.api.rest.controllers.CommandeRestController;
import com.nouha.api.rest.controllers.dto.request.PanierDto;
import com.nouha.api.rest.controllers.dto.response.CommandeShowEntityResponseDto;
import com.nouha.api.rest.controllers.dto.response.RestResponseDto;
import com.nouha.api.rest.data.entities.Commande;
import com.nouha.api.rest.data.services.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commandes")
public class CommandeRestControllerImpl implements CommandeRestController {
    private final CommandeService commandeService;
    @Override
    public ResponseEntity<Map<Object, Object>> commandes(int page, int size) {
        Page<Commande> commandes = commandeService.getAllCommande(PageRequest.of(page,size));
        Page<CommandeShowEntityResponseDto> commandeDto = commandes.map(CommandeShowEntityResponseDto::toDto);

        /*Map<Object,Object> model = new HashMap<Object,Object>();

        model.put("results",commandeDto.getContent());
        model.put("pages",new int[commandeDto.getTotalPages()]);
        model.put("currentPage",commandeDto.getNumber());
        model.put("PreviousPage", page > 0 ? page-1:page);
        model.put("nextPage", page < commandeDto.getTotalPages() - 1 ? page+1:page);

        model.put("totalItems",commandeDto.getTotalElements());
        model.put("totalPages",commandeDto.getTotalPages());*/

        Map<Object,Object> response = RestResponseDto.response(
                commandeDto.getContent(),
                new int[commandeDto.getTotalPages()],
                commandeDto.getNumber(),
                page > 0 ? page-1:page,
                page < commandeDto.getTotalPages() - 1 ? page+1:page,
                commandeDto.getTotalElements(),
                commandeDto.getTotalPages(),
                HttpStatus.OK
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object,Object> > commandeUnClient(int page, int size, long id) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Commande> commandes = commandeService.getCommandeByClient(id,pageable);
        //transformer objet type Commande => commandeDto
        Page<CommandeShowEntityResponseDto> commandesDto = commandes.map(CommandeShowEntityResponseDto::toDto);

        Map<Object,Object> response = RestResponseDto.response(
                commandesDto.getContent(),
                new int[commandesDto.getTotalPages()],
                commandesDto.getNumber(),
                page > 0 ? page-1:page,
                page < commandesDto.getTotalPages() - 1 ? page+1:page,
                commandesDto.getTotalElements(),
                commandesDto.getTotalPages(),
                HttpStatus.OK
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object, Object>> saveCommande(PanierDto panier) {
        commandeService.saveCommande(panier);
        //204
        return new ResponseEntity<>(RestResponseDto.response(null,HttpStatus.NO_CONTENT), HttpStatus.OK);
    }


}