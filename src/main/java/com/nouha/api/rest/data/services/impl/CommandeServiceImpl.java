package com.nouha.api.rest.data.services.impl;

import com.nouha.api.rest.controllers.dto.request.PanierDto;
import com.nouha.api.rest.data.entities.Article;
import com.nouha.api.rest.data.entities.Client;
import com.nouha.api.rest.data.entities.Commande;
import com.nouha.api.rest.data.entities.LigneCommande;
import com.nouha.api.rest.data.enums.EtatCommande;
import com.nouha.api.rest.data.repositories.ArticleRepository;
import com.nouha.api.rest.data.repositories.ClientRepository;
import com.nouha.api.rest.data.repositories.CommandeRepository;
import com.nouha.api.rest.data.repositories.LigneCommandeRepository;
import com.nouha.api.rest.data.services.CommandeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {
    private  final CommandeRepository commandeRepository;
    private  final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    @Override
    public Page<Commande> getAllCommande(Pageable page) {
        return commandeRepository.findAllByActiveTrue(page);
    }

    @Override
    public Page<Commande> getCommandeByClient(Long id, Pageable pageable) {
        return commandeRepository.findCommandesByClientId(id,pageable);
    }

    @Override
    public void saveCommande(PanierDto panierDto) {
        //Ligne de commande, c'est une liste et panier.getArticlesPanier() et de type article dto → convertir Article donc on stream
        Client client = clientRepository.findById(panierDto.getClient().getId())
                .orElseThrow(()->new EntityNotFoundException("Le client n'existe pas"));

        //je transforme la chaine adresse en Adresse
        Commande commande = new Commande(
                new Date(),
                panierDto.getTotal(),
                EtatCommande.Encours,
                null,
                client,
                null
        );
        commande.setActive(true);

        commandeRepository.save(commande);
        //les ligne de commandes
        List<LigneCommande> ligneCommande = panierDto.getArticlesPanier().stream().map(
                articlePanierDto -> {
                    Article article = articleRepository.findById(articlePanierDto.getId()).orElseThrow(
                            ()-> new EntityNotFoundException("L'article n'existe pas ! ")
                    );

                    return new LigneCommande(
                            true,
                            articlePanierDto.getMontant(),
                            articlePanierDto.getQuantite(),
                            articlePanierDto.getPrix(),
                            article,
                            commande
                    );
                }).toList();

        ligneCommandeRepository.saveAllAndFlush(ligneCommande);


    }
}
