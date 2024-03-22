package com.nouha.api.rest.data.entities;



import com.nouha.api.rest.data.security.data.entities.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "clients")
@Builder
@DiscriminatorValue(value="Client")
public class Client extends AppUser {
    @Column(nullable = false,length = 50)
    private String nomComplet;
    @Column(nullable = false,length = 20)
    private String telephone;
    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Commande> commandes;
}
