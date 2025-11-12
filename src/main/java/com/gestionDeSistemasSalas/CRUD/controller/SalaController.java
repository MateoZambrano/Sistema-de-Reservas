package com.gestionDeSistemasSalas.CRUD.controller;

import com.gestionDeSistemasSalas.CRUD.model.Sala;
import com.gestionDeSistemasSalas.CRUD.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {
    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public List<Sala> getAll() {
        return salaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Sala getById(@PathVariable Long id) {
        return salaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Sala create(@RequestBody Sala sala) {
        return salaRepository.save(sala);
    }

    @PutMapping("/{id}")
    public Sala update(@PathVariable Long id, @RequestBody Sala sala) {
        sala.setId_sala(id);
        return salaRepository.save(sala);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        salaRepository.deleteById(id);
    }
}
