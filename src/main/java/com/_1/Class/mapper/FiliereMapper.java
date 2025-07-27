package com._1.Class.mapper;

import com._1.Class.dto.CreateFiliereRequest;
import com._1.Class.dto.CreateFiliereResponse;
import com._1.Class.model.Filiere;
import org.springframework.stereotype.Component;

@Component
public class FiliereMapper {
    public Filiere fromCreateFiliereRequestToFiliere(CreateFiliereRequest request) {
        return Filiere.builder()
                .libelle(request.getLibelle())
                .build();
    }

    public CreateFiliereResponse fromFiliereToCreateFiliereResponse(Filiere filiere) {
        return CreateFiliereResponse.builder()
                .id(filiere.getId())
                .libelle(filiere.getLibelle())
                .build();
    }
} 