package com._1.Class.mapper;

import com._1.Class.dto.CreateClasseRequest;
import com._1.Class.dto.CreateClasseResponse;
import com._1.Class.model.Classe;
import com._1.Class.model.Filiere;
import com._1.Class.dto.FiliereDto;
import org.springframework.stereotype.Component;

@Component
public class ClasseMapper {
    public Classe fromCreateClasseRequestToClasse(CreateClasseRequest request) {
        return Classe.builder()
                .code(request.getCode())
                .libelle(request.getLibelle())
                .fraisInscription(request.getFraisInscription())
                .mensualite(request.getMensualite())
                .autreFrais(request.getAutreFrais())
                .nombrePlace(request.getNombrePlace())
                .filiere(null)
                .build();
    }

    public CreateClasseResponse fromClasseToCreateClasseResponse(Classe classe) {
        FiliereDto filiereDto = null;
        if (classe.getFiliere() != null) {
            filiereDto = FiliereDto.builder()
                .id(classe.getFiliere().getId())
                .libelle(classe.getFiliere().getLibelle())
                .build();
        }
        return CreateClasseResponse.builder()
                .id(classe.getId())
                .code(classe.getCode())
                .libelle(classe.getLibelle())
                .fraisInscription(classe.getFraisInscription())
                .mensualite(classe.getMensualite())
                .autreFrais(classe.getAutreFrais())
                .nombrePlace(classe.getNombrePlace())
                .filiere(filiereDto)
                .build();
    }
} 