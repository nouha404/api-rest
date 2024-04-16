package com.nouha.api.rest.controllers;

import com.nouha.api.rest.controllers.dto.request.CreateClientRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


public interface ClientRestController {

    @GetMapping("/clients")
    //pas obligé de mettre un type de retour Map<Object,Object>

    ResponseEntity<?> listerClient(
                        @RequestParam(defaultValue = "0",name = "page") int page,
                        @RequestParam(defaultValue = "8",name = "size") int size,
                        @RequestParam(defaultValue = "",name = "telephone") String telephone
                        );


    @PostMapping("/clients")
    ResponseEntity<?> saveClient(@Valid @RequestBody  CreateClientRequestDto clientDto,
                                 BindingResult bindingResult);

}
