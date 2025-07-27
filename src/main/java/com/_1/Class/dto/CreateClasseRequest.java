package com._1.Class.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClasseRequest {
    @NotBlank(message = "Le code est obligatoire")
    private String code;
    @NotBlank(message = "Le libelle est obligatoire")
    private String libelle;
    @NotNull(message = "Frais d'incription obligatoire")
    @Min(value = 20000, message = "minimum 20000 fr")
    @Max(value = Integer.MAX_VALUE, message = "la valeur ne doit pas depasser la limite d'un long")
    private Integer fraisInscription;
    @NotNull(message = "Mensualite obligatoire")
    @Min(value = 25000, message = "minimum 25000 fr")
    @Max(value = Integer.MAX_VALUE, message = "la valeur ne doit pas depasser la limite      d'un long")
    private Integer mensualite;
    @NotNull(message = "Autre frais obligatoire")
    @Min(value = 15000, message = "minimum 15000 fr")
    @Max(value = Integer.MAX_VALUE, message = "la valeur ne doit pas depasser la limite d'un long")
    private Integer autreFrais;
    @NotNull(message = "Nombre de places obligatoire")
    @Min(value = 1, message = "minimum 1 place")
    @Max(value = Integer.MAX_VALUE, message = "la valeur ne doit pas depasser la limite d'un long")
    private Integer nombrePlace;
    @NotNull(message = "Filiere obligatoire")
    private long filiereId;
} 