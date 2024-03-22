package com.nouha.api.rest.data.security.data.fixtures;

import com.nouha.api.rest.data.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
@Order(2) //je charge avant le role 1
public class AppUserFixtures implements CommandLineRunner {
    //injection de dependance avec @RequiredArgsConstructor
    private final SecurityService securityService;

    @Override
    public void run(String... args) throws Exception {

        securityService.saveUser("nouha","nouha12b");
        securityService.addRoleToUser("nouha","Admin");
        securityService.addRoleToUser("nouha","Client");
    }
}
