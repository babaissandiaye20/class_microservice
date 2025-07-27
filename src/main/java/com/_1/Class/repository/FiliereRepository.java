package com._1.Class.repository;

import com._1.Class.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    @Query("SELECT f FROM Filiere f WHERE f.isDeleted = false")
    List<Filiere> findAllNotDeleted();

    @Query(value = "SELECT f FROM Filiere f WHERE f.libelle != :libelle")
    java.util.List<Filiere> findFilieresWithoutThisLibelle(String libelle);
} 