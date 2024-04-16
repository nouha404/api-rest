package com.nouha.api.rest.controllers;

import com.nouha.api.rest.controllers.dto.request.PanierDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


public interface CommandeRestController {
    @GetMapping("")
    ResponseEntity<Map<Object,Object>> commandes(
            @RequestParam(defaultValue = "0",name = "page") int page,
            @RequestParam(defaultValue = "5",name = "size") int size
    );

    @GetMapping("/client/{id}")
    ResponseEntity<Map<Object,Object>> commandeUnClient(
                         @RequestParam(defaultValue = "0",name = "page") int page,
                         @RequestParam(defaultValue = "5",name = "size") int size,
                         @PathVariable(name="id",required = false) long id
    );

    @PostMapping("")
    ResponseEntity<Map<Object,Object>> saveCommande(
            PanierDto panier
    );

}
