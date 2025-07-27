package com._1.Class.controller;

import com._1.Class.dto.ApiResponse;
import com._1.Class.dto.CreateFiliereRequest;
import com._1.Class.dto.CreateFiliereResponse;
import com._1.Class.dto.UpdateFiliereRequest;
import com._1.Class.helper.FiliereHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {
    private final FiliereHelper filiereHelper;

    public FiliereController(FiliereHelper filiereHelper) {
        this.filiereHelper = filiereHelper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateFiliereResponse>> save(@RequestBody @Valid CreateFiliereRequest filiereRequest) {
        ApiResponse<CreateFiliereResponse> response = filiereHelper.save(filiereRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CreateFiliereResponse>>> allFilieres() {
        ApiResponse<List<CreateFiliereResponse>> response = filiereHelper.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CreateFiliereResponse>> getById(@PathVariable long id) {
        ApiResponse<CreateFiliereResponse> response = filiereHelper.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CreateFiliereResponse>> update(@RequestBody UpdateFiliereRequest updateFiliereRequest) {
        ApiResponse<CreateFiliereResponse> response = filiereHelper.update(updateFiliereRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {
        ApiResponse<String> response = filiereHelper.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
} 