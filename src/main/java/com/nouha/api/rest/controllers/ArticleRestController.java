package com.nouha.api.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ArticleRestController {
    @GetMapping("/articles/libelle/{libelle}")
    ResponseEntity<?> listerArticleByLibelle(
            @PathVariable String libelle

    );
}
