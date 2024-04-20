package com.nouha.api.rest.data.services;

import com.nouha.api.rest.controllers.dto.request.PanierDto;
import com.nouha.api.rest.data.entities.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommandeService {
    Page<Commande> getAllCommande(Pageable page);
    Page<Commande> getCommandeByClient(Long id, Pageable pageable);
    void saveCommande(PanierDto panierDto);
}
