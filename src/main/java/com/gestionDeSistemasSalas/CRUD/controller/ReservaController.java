package com.gestionDeSistemasSalas.CRUD.controller;


import com.gestionDeSistemasSalas.CRUD.model.Reserva;
import com.gestionDeSistemasSalas.CRUD.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public List<Reserva> getAll() {
        return reservaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reserva getById(@PathVariable Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Reserva create(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @PutMapping("/{id}")
    public Reserva update(@PathVariable Long id, @RequestBody Reserva reserva) {
        reserva.setId_reserva(id);
        return reservaRepository.save(reserva);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reservaRepository.deleteById(id);
    }
}
