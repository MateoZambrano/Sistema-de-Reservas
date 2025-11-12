package com.gestionDeSistemasSalas.CRUD.view;

import com.gestionDeSistemasSalas.CRUD.model.Sala;
import com.gestionDeSistemasSalas.CRUD.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SalasView {

    @Autowired
    private SalaRepository salaRepository;

    //Listar todas las salas
    @GetMapping("/view/salas")
    public String listarSalas(Model model) {
        model.addAttribute("salas", salaRepository.findAll());
        return "salas"; // archivo: templates/salas.html
    }

    //Formulario para crear nueva sala
    @GetMapping("/view/salas/form")
    public String formularioSala(Model model) {
        model.addAttribute("sala", new Sala());
        return "salasForm"; // archivo: templates/salasForm.html
    }

    // Guardar nueva sala o actualizar existente
    @PostMapping("/view/salas/save")
    public String guardarSala(@ModelAttribute Sala sala, RedirectAttributes ra) {
        salaRepository.save(sala);
        ra.addFlashAttribute("message", "Sala guardada correctamente");
        return "redirect:/view/salas";
    }

    // Editar sala existente
    @GetMapping("/view/salas/edit/{id}")
    public String editarSala(@PathVariable Long id, Model model) {
        Sala sala = salaRepository.findById(id).orElse(null);
        model.addAttribute("sala", sala);
        return "salasForm"; // reutiliza el mismo formulario
    }

    // Eliminar sala
    @PostMapping("/view/salas/delete/{id}")
    public String eliminarSala(@PathVariable Long id, RedirectAttributes ra) {
        salaRepository.deleteById(id);
        ra.addFlashAttribute("message", "Sala eliminada correctamente");
        return "redirect:/view/salas";
    }
}
