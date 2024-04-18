package com.nouha.api.rest.controllers.dto.request;

import com.nouha.api.rest.data.entities.Article;
import lombok.*;

import java.util.Objects;


@Getter
@Setter
@Builder
public class ArticlePanierDto {
    private Long id;
    private String libelle;
    private Double montant=0.0;
    //@Positive(message = "Le prix doit être un nombre positif.")
    private Double quantite;
    //@Positive(message = "Le prix doit être un nombre positif.")
    private Double prix;

    public Double calculQte(Double newQte){
        if (newQte != null) {
            quantite += newQte;
        }

        return quantite;
    }

    public Double calculMontant(Double newMontant){
        if (newMontant != null) {
            montant += newMontant;
        }
        return montant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticlePanierDto that = (ArticlePanierDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static ArticlePanierDto toDto(Article article) {
        return ArticlePanierDto
                .builder()
                .id(article.getId())
                .libelle(article.getLibelle())
                .quantite(article.getQteStock())
                .prix(article.getNouveauPrix())
                .build();
    }
}