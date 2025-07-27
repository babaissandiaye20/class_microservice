package com._1.Class.helper;

import com._1.Class.dto.ApiResponse;
import com._1.Class.dto.CreateClasseRequest;
import com._1.Class.dto.CreateClasseResponse;
import com._1.Class.dto.UpdateClasseRequest;
import com._1.Class.exception.AlreadyExistsException;
import com._1.Class.exception.NotFoundException;
import com._1.Class.mapper.ClasseMapper;
import com._1.Class.model.Classe;
import com._1.Class.model.Filiere;
import com._1.Class.service.IClasse;
import com._1.Class.service.IFiliere;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClasseHelper {
    private final IClasse iClasse;
    private final ClasseMapper classeMapper;
    private final IFiliere iFiliere;

    public ClasseHelper(IClasse iClasse, ClasseMapper classeMapper, IFiliere iFiliere) {
        this.iClasse = iClasse;
        this.classeMapper = classeMapper;
        this.iFiliere = iFiliere;
    }

    public ApiResponse<CreateClasseResponse> save(CreateClasseRequest classeRequest) {
        if (iClasse.findByCode(classeRequest.getCode()) != null) {
            throw new AlreadyExistsException("Une classe avec le code [ " + classeRequest.getCode() + " ] existe déjà!");
        }
        if (iClasse.findByLibelle(classeRequest.getLibelle()) != null) {
            throw new AlreadyExistsException("Une classe avec le libellé [ " + classeRequest.getLibelle() + " ] existe déjà!");
        }
        Classe classe = classeMapper.fromCreateClasseRequestToClasse(classeRequest);
        Filiere filiere = iFiliere.findById(classeRequest.getFiliereId());
        if (filiere == null) {
            throw new NotFoundException("Filière avec id " + classeRequest.getFiliereId() + " introuvable");
        }
        classe.setFiliere(filiere);
        classe = iClasse.save(classe);
        CreateClasseResponse response = classeMapper.fromClasseToCreateClasseResponse(classe);
        return ApiResponse.<CreateClasseResponse>builder()
                .message("Classe créée avec succès")
                .date(LocalDateTime.now())
                .status(201)
                .data(response)
                .build();
    }

    public ApiResponse<List<CreateClasseResponse>> findAll() {
        List<CreateClasseResponse> list = iClasse.findAll().stream()
                .map(classeMapper::fromClasseToCreateClasseResponse)
                .collect(Collectors.toList());
        return ApiResponse.<List<CreateClasseResponse>>builder()
                .message("Liste des classes récupérée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data(list)
                .build();
    }

    public ApiResponse<CreateClasseResponse> findById(long id) {
        Classe classe = iClasse.findById(id);
        if (classe == null) {
            throw new NotFoundException("Classe avec id " + id + " introuvable");
        }
        CreateClasseResponse response = classeMapper.fromClasseToCreateClasseResponse(classe);
        return ApiResponse.<CreateClasseResponse>builder()
                .message("Classe trouvée")
                .date(LocalDateTime.now())
                .status(200)
                .data(response)
                .build();
    }

    public ApiResponse<CreateClasseResponse> update(UpdateClasseRequest updateClasseRequest) {
        Classe classe = iClasse.findById(updateClasseRequest.getId());
        if (classe == null) {
            throw new NotFoundException("La classe avec l'id " + updateClasseRequest.getId() + " n'existe pas");
        }
        List<String> codes = iClasse.findClassesWithoutThisCode(updateClasseRequest.getCreateClasseRequest().getCode()).stream()
                .map(Classe::getCode).toList();
        if (codes.contains(updateClasseRequest.getCreateClasseRequest().getCode())) {
            throw new AlreadyExistsException("Une classe avec le code [ " + updateClasseRequest.getCreateClasseRequest().getCode() + " ] existe déjà!");
        }
        List<String> libelles = iClasse.findClassesWithoutThisLibelle(updateClasseRequest.getCreateClasseRequest().getLibelle()).stream()
                .map(Classe::getLibelle).toList();
        if (libelles.contains(updateClasseRequest.getCreateClasseRequest().getLibelle())) {
            throw new AlreadyExistsException("Une classe avec le libellé [ " + updateClasseRequest.getCreateClasseRequest().getLibelle() + " ] existe déjà!");
        }
        Classe classeToUpdate = classeMapper.fromCreateClasseRequestToClasse(updateClasseRequest.getCreateClasseRequest());
        classeToUpdate.setId(classe.getId());
        Filiere filiere = iFiliere.findById(updateClasseRequest.getCreateClasseRequest().getFiliereId());
        if (filiere == null) {
            throw new NotFoundException("Filière avec id " + updateClasseRequest.getCreateClasseRequest().getFiliereId() + " introuvable");
        }
        classeToUpdate.setFiliere(filiere);
        classe = iClasse.save(classeToUpdate);
        CreateClasseResponse response = classeMapper.fromClasseToCreateClasseResponse(classe);
        return ApiResponse.<CreateClasseResponse>builder()
                .message("Classe modifiée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data(response)
                .build();
    }

    public ApiResponse<String> delete(long id) {
        Classe classe = iClasse.findById(id);
        if (classe == null) {
            throw new NotFoundException("La classe avec l'id " + id + " n'existe pas");
        }
        iClasse.delete(classe);
        return ApiResponse.<String>builder()
                .message("Classe supprimée avec succès")
                .date(LocalDateTime.now())
                .status(200)
                .data("Classe supprimée")
                .build();
    }
} 