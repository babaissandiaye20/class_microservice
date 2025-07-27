package com._1.Class.service;

import com._1.Class.model.Filiere;
import java.util.List;

public interface IFiliere {
    List<Filiere> findAll();
    Filiere save(Filiere filiere);
    Filiere findById(long id);
    List<Filiere> findFilieresWithoutThisLibelle(String libelle);
    List<Filiere> findAllNotDeleted();
    void delete(Filiere filiere);
    Filiere update(long id, Filiere filiere);
} 