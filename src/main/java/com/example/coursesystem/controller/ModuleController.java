package com.example.coursesystem.controller;

import com.example.coursesystem.dto.ModuleDTO;
import com.example.coursesystem.service.impl.IModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/modules")
public class ModuleController {

    private final IModuleService moduleService;

    @PostMapping("/create")
    public ResponseEntity<ModuleDTO> createModule(@RequestBody ModuleDTO moduleDTO) {
        var module = moduleService.createModule(moduleDTO);
        return ResponseEntity.ok(module);
    }
}
