package com.gestionDeSistemasSalas.CRUD.view;

import com.gestionDeSistemasSalas.CRUD.model.RecursoAdicional;
import com.gestionDeSistemasSalas.CRUD.model.Sala;
import com.gestionDeSistemasSalas.CRUD.repository.RecursoAdicionalRepository;
import com.gestionDeSistemasSalas.CRUD.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;

@Controller
public class RecursoAdicionalView {

    @Autowired
    private RecursoAdicionalRepository recursoAdicionalRepository;

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping("/view/recursos")
    public String lista(Model model) {
        model.addAttribute("recursos", recursoAdicionalRepository.findAll());
        return "recursos";
    }

    @GetMapping("/view/recursos/form")
    public String formulario(Model model) {
        model.addAttribute("recurso", new RecursoAdicional());
        model.addAttribute("salas", salaRepository.findAll());
        return "recursosForm";
    }

    @PostMapping("/view/recursos/guardar")
    public String guardar(@ModelAttribute RecursoAdicional recurso, RedirectAttributes ra) {
        recursoAdicionalRepository.save(recurso);
        ra.addFlashAttribute("message", "Recurso adicional guardado correctamente");
        return "redirect:/view/recursos";
    }

    @GetMapping("/view/recursos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        RecursoAdicional recurso = recursoAdicionalRepository.findById(id).orElse(null);
        model.addAttribute("recurso", recurso);
        model.addAttribute("salas", salaRepository.findAll());
        return "recursosForm";
    }

    @PostMapping("/view/recursos/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        recursoAdicionalRepository.deleteById(id);
        ra.addFlashAttribute("message", "Recurso adicional eliminado correctamente");
        return "redirect:/view/recursos";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Sala.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty()) {
                    setValue(null); // <- evita NumberFormatException
                } else {
                    try {
                        Long id = Long.parseLong(text);
                        Sala sala = salaRepository.findById(id).orElse(null);
                        setValue(sala);
                    } catch (NumberFormatException e) {
                        setValue(null);
                    }
                }
            }
        });
    }
}
