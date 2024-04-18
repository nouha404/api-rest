package com.nouha.api.rest.data.services;

import com.nouha.api.rest.data.entities.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> getArticlesFormCommande();
    public Article getArticleByLibelle(String libelle);
}
