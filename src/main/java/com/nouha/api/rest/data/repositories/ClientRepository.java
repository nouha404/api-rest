package com.nouha.api.rest.data.repositories;


import com.nouha.api.rest.data.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Page<Client> findAllByActiveTrue(Pageable pageable);
    Page<Client> findAllByTelephoneContainsAndActiveTrue(Pageable pageable,String telephone);
    Client findClientByTelephoneAndActiveTrue(String tel);

    //Pour le test dans la console
    List<Client> findAllByActiveTrue();
    List<Client> findClientById(Long id);


}
