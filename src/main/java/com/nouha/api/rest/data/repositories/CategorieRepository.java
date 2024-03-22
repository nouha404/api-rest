package com.nouha.api.rest.data.repositories;

import com.nouha.api.rest.data.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    //Client findClientById(int id);
    //Categorie findById
}
