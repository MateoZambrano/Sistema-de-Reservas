package com.gestionDeSistemasSalas.CRUD.controller;


import com.gestionDeSistemasSalas.CRUD.model.RecursoAdicional;
import com.gestionDeSistemasSalas.CRUD.repository.RecursoAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoAdicionalController {
    @Autowired
    private RecursoAdicionalRepository recursoAdicionalRepository;

    @GetMapping
    public List<RecursoAdicional> getAll() {
        return recursoAdicionalRepository.findAll();
    }

    @GetMapping("/{id}")
    public RecursoAdicional getById(@PathVariable Long id) {
        return recursoAdicionalRepository.findById(id).orElse(null);
    }

    @PostMapping
    public RecursoAdicional create(@RequestBody RecursoAdicional recursoAdicional) {
        return recursoAdicionalRepository.save(recursoAdicional);
    }

    @PutMapping("/{id}")
    public RecursoAdicional update(@PathVariable Long id, @RequestBody RecursoAdicional usuario) {
        usuario.setId_recurso_adicional(id);
        return recursoAdicionalRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recursoAdicionalRepository.deleteById(id);
    }
}
