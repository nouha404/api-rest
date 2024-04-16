package com.nouha.api.rest.controllers.dto.request;

import com.nouha.api.rest.data.entities.Adresse;
import com.nouha.api.rest.data.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateClientRequestDto {
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    private String nomComplet;
    // @NotBlank(message = "Le telephone est obligatoire")
    //@Size(min = 9, message = "Le telephone doit avoir au minimum 9 chiffres ")
    @Pattern(regexp = "[0-9]{9}", message = "Le Telephone doit avoir 9 chiffres")
    private String telephone;
    @NotBlank(message = "Le quartier est obligatoire")
    private String quartier;
    @NotBlank(message = "Le numero villa est obligatoire")
    private String numVilla;
    @NotBlank(message = "La ville est obligatoire")
    private String ville;

    //Le champ doit contenir uniquement des chiffres et doit comporter exactement 9 chiffres. Mapper
    public Client TransformToEntity(){
        return Client.builder()
                .nomComplet(nomComplet)
                .telephone(telephone)
                .adresse(new Adresse(quartier,ville,numVilla))
                .build();
    }

    public static CreateClientRequestDto toDto(Client client){
        return CreateClientRequestDto.builder()
                .id(client.getId())
                .nomComplet(client.getNomComplet())
                .telephone(client.getTelephone())
                .quartier(client.getAdresse().getQuartier())
                .numVilla(client.getAdresse().getNumVilla())
                .ville(client.getAdresse().getVille())

                .build();

    }
}
