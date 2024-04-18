package com.nouha.api.rest.controllers.impl;

import com.nouha.api.rest.controllers.ArticleRestController;
import com.nouha.api.rest.controllers.dto.request.ArticlePanierDto;
import com.nouha.api.rest.controllers.dto.response.ClientShowEntityResponseDto;
import com.nouha.api.rest.controllers.dto.response.RestResponseDto;
import com.nouha.api.rest.data.entities.Article;
import com.nouha.api.rest.data.entities.Client;
import com.nouha.api.rest.data.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleRestControllerImpl implements ArticleRestController {
    private final ArticleService articleService;
    @Override
    public ResponseEntity<Map<Object,Object>> listerArticleByLibelle(String libelle) {

        Article article = articleService.getArticleByLibelle(libelle);
        Map<Object, Object> response;

        if (article==null){
            response= RestResponseDto.response(null, HttpStatus.NO_CONTENT);
        } else {
            response = RestResponseDto.response(ArticlePanierDto.toDto(article),HttpStatus.CREATED);
        }

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
