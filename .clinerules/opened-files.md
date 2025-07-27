# Opened Files
## File Name
src/main/java/com/_1/Class/helper/FiliereHelper.java
## File Content
package com._1.Class.helper;

import com._1.Class.dto.ApiResponse;
import com._1.Class.dto.CreateFiliereRequest;
import com._1.Class.dto.CreateFiliereResponse;
import com._1.Class.dto.UpdateFiliereRequest;
import com._1.Class.exception.AlreadyExistsException;
import com._1.Class.exception.NotFoundException;
import com._1.Class.mapper.FiliereMapper;
import com._1.Class.model.Filiere;
import com._1.Class.service.IFiliere;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FiliereHelper {
    private final IFiliere iFiliere;
    private final FiliereMapper filiereMapper;

    public FiliereHelper(IFiliere iFiliere, FiliereMapper filiereMapper) {
        this.iFiliere = iFiliere;
        this.filiereMapper = filiereMapper;
    }

    public ApiResponse<CreateFiliereResponse> save(CreateFiliereRequest filiereRequest) {
        List<Filiere> exist = iFiliere.findFilieresWithoutThisLibelle(filiereRequest.getLibelle());
        if (exist.stream().anyMatch(f -> f.getLibelle().equalsIgnoreCase(filiereRequest.getLibelle()))) {
            throw new AlreadyExistsException("Une filière avec le libellé [ " + filiereRequest.getLibelle() + " ] existe déjà!");
        }
        Filiere filiere = filiereMapper.fromCreateFiliereRequestToFiliere(filiereRequest);
        filiere = iFiliere.save(filiere);
        CreateFiliereResponse response = filiereMapper.fromFiliereToCreateFiliereResponse(filiere);
        return ApiResponse.<CreateFiliereResponse>builder()
                .message("Filière créée avec succès")
                .date(LocalDateTime.now())
                .status(201)
                .data(response)
                .build();
    }

    public ApiResponse<List<CreateFiliereResponse>> findAll() {
        List<CreateFiliereResponse> list = iFiliere.findAll().stream()
                .map(filiereMapper::fromFiliereToCreateFiliereResponse)
                .collect(Collectors.toList());
        return ApiResponse.<List<CreateFiliereResponse>>builder()
                .message("Liste des filières récupérée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data(list)
                .build();
    }

    public ApiResponse<CreateFiliereResponse> findById(long id) {
        Filiere filiere = iFiliere.findById(id);
        if (filiere == null) {
            throw new NotFoundException("Filière avec id " + id + " introuvable");
        }
        CreateFiliereResponse response = filiereMapper.fromFiliereToCreateFiliereResponse(filiere);
        return ApiResponse.<CreateFiliereResponse>builder()
                .message("Filière trouvée")
                .date(LocalDateTime.now())
                .status(200)
                .data(response)
                .build();
    }

    public ApiResponse<CreateFiliereResponse> update(UpdateFiliereRequest updateFiliereRequest) {
        Filiere filiere = iFiliere.findById(updateFiliereRequest.getId());
        if (filiere == null) {
            throw new NotFoundException("La filière avec l'id " + updateFiliereRequest.getId() + " n'existe pas");
        }
        List<Filiere> exist = iFiliere.findFilieresWithoutThisLibelle(updateFiliereRequest.getCreateFiliereRequest().getLibelle());
        if (exist.stream().anyMatch(f -> f.getLibelle().equalsIgnoreCase(updateFiliereRequest.getCreateFiliereRequest().getLibelle()))) {
            throw new AlreadyExistsException("Une filière avec le libellé [ " + updateFiliereRequest.getCreateFiliereRequest().getLibelle() + " ] existe déjà!");
        }
        filiere.setLibelle(updateFiliereRequest.getCreateFiliereRequest().getLibelle());
        filiere = iFiliere.save(filiere);
        CreateFiliereResponse response = filiereMapper.fromFiliereToCreateFiliereResponse(filiere);
        return ApiResponse.<CreateFiliereResponse>builder()
                .message("Filière modifiée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data(response)
                .build();
    }

    public ApiResponse<String> delete(long id) {
        Filiere filiere = iFiliere.findById(id);
        if (filiere == null) {
            throw new NotFoundException("La filière avec l'id " + id + " n'existe pas");
        }
        iFiliere.delete(filiere);
        return ApiResponse.<String>builder()
                .message("Filière supprimée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data("Filière supprimée")
                .build();
    }
} 
