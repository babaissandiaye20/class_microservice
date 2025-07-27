package com._1.Class.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFiliereRequest {
    private long id;
    private CreateFiliereRequest createFiliereRequest;
} 