package com.nouha.api.rest.controllers.dto.response;

import com.nouha.api.rest.data.entities.Article;
import com.nouha.api.rest.data.entities.Categorie;
import com.nouha.api.rest.data.entities.LigneCommande;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ArticleResponseDto {
    private Long id;
    private String libelle;
    private Double ancienPrix;
    private Double nouveauPrix;
    private Boolean promo;
    private Double qteStock;
    private String photo;

    public static ArticleResponseDto toDto(Article article){
        return ArticleResponseDto
                .builder()
                .id(article.getId())
                .libelle(article.getLibelle())
                .ancienPrix(article.getAncienPrix())
                .nouveauPrix(article.getNouveauPrix())
                .promo(article.getPromo())
                .qteStock(article.getQteStock())
                .photo(article.getPhoto())
                .build();
    }

}
