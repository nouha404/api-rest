package com.nouha.api.rest.data.security.data.fixtures;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import com.nouha.api.rest.data.security.services.SecurityService;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
@Order(1)
public class AppRoleFixtures implements CommandLineRunner {
    //injection de dependance avec @RequiredArgsConstructor
    private final SecurityService securityService;

    @Override
    public void run(String... args) throws Exception {
        securityService.saveRole("Admin");
        securityService.saveRole("Client");
    }
}
