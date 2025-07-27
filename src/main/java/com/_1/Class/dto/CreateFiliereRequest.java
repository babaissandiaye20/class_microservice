package com._1.Class.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFiliereRequest {
    @NotBlank(message = "Le libelle est obligatoire")
    private String libelle;
} 