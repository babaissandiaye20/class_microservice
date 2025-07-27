package com._1.Class.service;

import com._1.Class.model.Filiere;
import com._1.Class.repository.FiliereRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FiliereService implements IFiliere {
    private final FiliereRepository filiereRepository;

    public FiliereService(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    @Override
    public List<Filiere> findAll() {
        return filiereRepository.findAll();
    }

    @Override
    public Filiere save(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    @Override
    public Filiere findById(long id) {
        return filiereRepository.findById(id).orElse(null);
    }

    @Override
    public List<Filiere> findFilieresWithoutThisLibelle(String libelle) {
        return filiereRepository.findFilieresWithoutThisLibelle(libelle);
    }

    @Override
    public List<Filiere> findAllNotDeleted() {
        return filiereRepository.findAllNotDeleted();
    }

    @Override
    public void delete(Filiere filiere) {
        filiereRepository.delete(filiere);
    }

    @Override
    public Filiere update(long id, Filiere updatedFiliere) {
        return filiereRepository.save(updatedFiliere);
    }
} 