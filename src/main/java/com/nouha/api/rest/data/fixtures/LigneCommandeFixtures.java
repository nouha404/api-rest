package com.nouha.api.rest.data.fixtures;


import com.nouha.api.rest.data.entities.Article;
import com.nouha.api.rest.data.entities.Commande;
import com.nouha.api.rest.data.entities.LigneCommande;
import com.nouha.api.rest.data.repositories.ArticleRepository;
import com.nouha.api.rest.data.repositories.CommandeRepository;
import com.nouha.api.rest.data.repositories.LigneCommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(Ordered.LOWEST_PRECEDENCE - 5)
@RequiredArgsConstructor
//@Component
public class LigneCommandeFixtures implements CommandLineRunner {
    //injection de dependance avec @RequiredArgsConstructor
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ArticleRepository articleRepository;
    private final CommandeRepository commandeRepository;
    @Override
    public void run(String... args) throws Exception {
        for (long i = 1L; i < 21L; i++) {
            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setActive(i%2==0);
            ligneCommande.setPrix(5+i+000.0+i);
            ligneCommande.setQuantite(1+i+0+i+0.+i+1);

            ligneCommande.setMontant(ligneCommande.getPrix()*ligneCommande.getQuantite());

            //Recherche de l'article
            Article article = articleRepository.findById(i).orElse(null);
            ligneCommande.setArticle(article);

            //Recherche de la commande
            Commande commande = commandeRepository.findById(i).orElse(null);
            ligneCommande.setCommande(commande);
            //insert
            ligneCommandeRepository.save(ligneCommande);
        }
    }
}
