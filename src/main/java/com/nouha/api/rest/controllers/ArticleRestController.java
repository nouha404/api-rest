package com.nouha.api.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


public interface ArticleRestController {

    @GetMapping("")
    ResponseEntity<Map<Object,Object>> listerArticle(
    );
    @GetMapping("/libelle/{libelle}")
    ResponseEntity<?> listerArticleByLibelle(
            @PathVariable String libelle

    );

}
