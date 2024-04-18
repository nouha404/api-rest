package com.nouha.api.rest.data.repositories;


import com.nouha.api.rest.data.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByActiveTrue();
    Article findByLibelleAndActiveTrue(String libelle);
}
