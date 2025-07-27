package com._1.Class.service;

import com._1.Class.model.Classe;
import java.util.List;

public interface IClasse {
    List<Classe> findAll();
    Classe save(Classe classe);
    Classe findById(long id);
    Classe findByCode(String code);
    Classe findByLibelle(String libelle);
    List<Classe> findClassesWithoutThisCode(String code);
    List<Classe> findClassesWithoutThisLibelle(String libelle);
    List<Classe> findAllNotDeleted();
    void delete(Classe classe);
    Classe update(long id, Classe classe);
} 