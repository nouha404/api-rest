package com.nouha.api.rest.data.security.repositories;

import com.nouha.api.rest.data.security.data.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
