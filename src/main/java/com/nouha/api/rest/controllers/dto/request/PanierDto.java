package com.nouha.api.rest.controllers.dto.request;

import com.nouha.api.rest.controllers.dto.response.ClientShowEntityResponseDto;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PanierDto {
    private List<ArticlePanierDto> articlesPanier;
    private double total;
    private ClientShowEntityResponseDto client;


    public void addArticleToPanier(ArticlePanierDto article) {
        int i = articlesPanier.indexOf(article);
        double montant = 0.0;
        if(i!=-1){
            ArticlePanierDto articleGetPanier = articlesPanier.get(i);
            articleGetPanier.calculQte(article.getQuantite());
            articleGetPanier.calculMontant(article.getQuantite()*articleGetPanier.getPrix());
            total=article.getQuantite()*articleGetPanier.getPrix();
        }else{
            article.setMontant(article.getPrix()*article.getQuantite());
            articlesPanier.add(article);
            total+=article.getMontant();
        }

    }



}
