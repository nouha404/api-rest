package com.nouha.api.rest.data.services.impl;

import com.nouha.api.rest.data.entities.Commande;
import com.nouha.api.rest.data.repositories.ArticleRepository;
import com.nouha.api.rest.data.repositories.ClientRepository;
import com.nouha.api.rest.data.repositories.CommandeRepository;
import com.nouha.api.rest.data.repositories.LigneCommandeRepository;
import com.nouha.api.rest.data.services.CommandeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
