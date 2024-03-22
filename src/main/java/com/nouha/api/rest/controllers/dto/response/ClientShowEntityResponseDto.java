package com.nouha.api.rest.controllers.dto.response;


import com.nouha.api.rest.data.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClientShowEntityResponseDto {
    private Long id;
    private String nomComplet;
    private String telephone;
    private String quartier;
    private String numVilla;
    private String adresseComplet;
    //transformer le type client en un type dto => mapper sans builder
    public static ClientShowEntityResponseDto toDto(Client client){
        ClientShowEntityResponseDto clientShowEntityResponseDto = new ClientShowEntityResponseDto(
                client.getId(),
                client.getNomComplet(),
                client.getTelephone(),
                client.getAdresse().getQuartier(),
                client.getAdresse().getNumVilla(),
                null
        );
        clientShowEntityResponseDto.adresseComplet();
        return clientShowEntityResponseDto;



    }
    private void adresseComplet(){
        adresseComplet =  String.format("%s %s", quartier,numVilla);
    }


}
