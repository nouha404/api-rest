package com.nouha.api.rest.data.repositories;


import com.nouha.api.rest.data.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Long> {
}
