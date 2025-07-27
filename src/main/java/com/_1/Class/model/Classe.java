package com._1.Class.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classe extends BaseModels {
    @Column(length = 25, unique = true)
    private String code;
    @Column(length = 100, unique = true)
    private String libelle;
    private int fraisInscription;
    private int mensualite;
    private int autreFrais;
    private int nombrePlace;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
} 