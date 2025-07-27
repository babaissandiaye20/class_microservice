package com._1.Class.service;

import com._1.Class.model.Classe;
import com._1.Class.repository.ClasseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClasseService implements IClasse {
    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    @Override
    public List<Classe> findAll() {
        return classeRepository.findAll();
    }

    @Override
    public Classe save(Classe classe) {
        return classeRepository.save(classe);
    }

    @Override
    public Classe findById(long id) {
        return classeRepository.findById(id).orElse(null);
    }

    @Override
    public Classe findByCode(String code) {
        return classeRepository.findByCode(code);
    }

    @Override
    public Classe findByLibelle(String libelle) {
        return classeRepository.findByLibelle(libelle);
    }

    @Override
    public List<Classe> findClassesWithoutThisCode(String code) {
        return classeRepository.findClassesWithoutThisCode(code);
    }

    @Override
    public List<Classe> findClassesWithoutThisLibelle(String libelle) {
        return classeRepository.findClassesWithoutThisLibelle(libelle);
    }

    @Override
    public List<Classe> findAllNotDeleted() {
        return classeRepository.findAllNotDeleted();
    }

    @Override
    public void delete(Classe classe) {
        classeRepository.delete(classe);
    }

    @Override
    public Classe update(long id, Classe updatedClasse) {
        return classeRepository.save(updatedClasse);
    }
} 