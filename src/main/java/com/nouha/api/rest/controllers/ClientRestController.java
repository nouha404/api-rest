package com.nouha.api.rest.controllers;

import com.nouha.api.rest.controllers.dto.request.CreateClientRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


public interface ClientRestController {

    @GetMapping("/clients")
    ResponseEntity<Map<Object,Object>> listerClient(
                        @RequestParam(defaultValue = "0",name = "page") int page,
                        @RequestParam(defaultValue = "8",name = "size") int size,
                        @RequestParam(defaultValue = "",name = "telephone") String telephone
                        );


    @PostMapping("/clients")
    ResponseEntity<Map<Object,Object>> saveClient(@RequestBody  CreateClientRequestDto clientDto);

}
