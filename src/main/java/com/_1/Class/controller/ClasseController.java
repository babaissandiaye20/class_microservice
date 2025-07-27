package com._1.Class.controller;

import com._1.Class.dto.ApiResponse;
import com._1.Class.dto.CreateClasseRequest;
import com._1.Class.dto.CreateClasseResponse;
import com._1.Class.dto.UpdateClasseRequest;
import com._1.Class.helper.ClasseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {
    private final ClasseHelper classeHelper;

    public ClasseController(ClasseHelper classeHelper) {
        this.classeHelper = classeHelper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateClasseResponse>> save(@RequestBody @Valid CreateClasseRequest classeRequest) {
        ApiResponse<CreateClasseResponse> response = classeHelper.save(classeRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CreateClasseResponse>>> allClasses() {
        ApiResponse<List<CreateClasseResponse>> response = classeHelper.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CreateClasseResponse>> getById(@PathVariable long id) {
        ApiResponse<CreateClasseResponse> response = classeHelper.findById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CreateClasseResponse>> update(@RequestBody @Valid UpdateClasseRequest updateClasseRequest) {
        ApiResponse<CreateClasseResponse> response = classeHelper.update(updateClasseRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long id) {
        ApiResponse<String> response = classeHelper.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
} 