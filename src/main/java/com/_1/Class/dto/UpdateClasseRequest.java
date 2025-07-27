package com._1.Class.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateClasseRequest {
    private long id;
    private CreateClasseRequest createClasseRequest;
} 