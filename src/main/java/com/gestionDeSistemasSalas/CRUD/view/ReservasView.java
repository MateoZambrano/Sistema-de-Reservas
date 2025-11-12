package com.gestionDeSistemasSalas.CRUD.view;

import com.gestionDeSistemasSalas.CRUD.model.Reserva;
import com.gestionDeSistemasSalas.CRUD.model.Sala;
import com.gestionDeSistemasSalas.CRUD.model.Usuario;
import com.gestionDeSistemasSalas.CRUD.repository.ReservaRepository;
import com.gestionDeSistemasSalas.CRUD.repository.SalaRepository;
import com.gestionDeSistemasSalas.CRUD.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;

@Controller
public class ReservasView {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping("/view/reservas")
    public String lista(Model model) {
        model.addAttribute("reservas", reservaRepository.findAll());
        return "reservas";
    }

    @GetMapping("/view/reservas/form")
    public String formulario(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("salas", salaRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "reservasForm";
    }

    @PostMapping("/view/reservas/guardar")
    public String guardar(@ModelAttribute Reserva reserva, RedirectAttributes ra) {
        reservaRepository.save(reserva);
        ra.addFlashAttribute("message", "Reserva guardada correctamente");
        return "redirect:/view/reservas";
    }

    @GetMapping("/view/reservas/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        model.addAttribute("reserva", reserva);
        return "reservasForm";
    }

    @PostMapping("/view/reservas/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        reservaRepository.deleteById(id);
        ra.addFlashAttribute("message", "Reserva eliminada correctamente");
        return "redirect:/view/reservas";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Sala.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    Long id = Long.parseLong(text);
                    Sala sala = salaRepository.findById(id).orElse(null);
                    setValue(sala);
                }
            }
        });

        binder.registerCustomEditor(Usuario.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    Long id = Long.parseLong(text);
                    Usuario usuario = usuarioRepository.findById(id).orElse(null);
                    setValue(usuario);
                }
            }
        });
    }
}

