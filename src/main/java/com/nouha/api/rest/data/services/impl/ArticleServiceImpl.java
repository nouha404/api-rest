package com.nouha.api.rest.data.services.impl;


import com.nouha.api.rest.data.entities.Article;
import com.nouha.api.rest.data.repositories.ArticleRepository;
import com.nouha.api.rest.data.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//injection de dependance
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    @Override
    public List<Article> getArticlesFormCommande() {
        return articleRepository.findAllByActiveTrue();
    }
}
