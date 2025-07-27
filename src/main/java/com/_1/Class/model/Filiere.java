package com._1.Class.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filiere extends BaseModels {
    @Column(length = 150, unique = true)
    private String libelle;
    @OneToMany(mappedBy = "filiere")
    private List<Classe> classes;
} 
