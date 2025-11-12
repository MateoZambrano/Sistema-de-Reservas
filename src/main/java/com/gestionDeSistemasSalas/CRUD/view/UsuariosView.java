package com.gestionDeSistemasSalas.CRUD.view;

import com.gestionDeSistemasSalas.CRUD.model.Usuario;
import com.gestionDeSistemasSalas.CRUD.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuariosView {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/view/usuarios")
    public String lista(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("/view/usuarios/form")
    public String formulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuariosForm";
    }

    @PostMapping("/view/usuarios/guardar")
    public String guardar(@ModelAttribute Usuario usuario, RedirectAttributes ra) {
        usuarioRepository.save(usuario);
        ra.addFlashAttribute("message", "Usuario guardado correctamente");
        return "redirect:/view/usuarios";
    }

    @GetMapping("/view/usuarios/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "usuariosForm";
    }

    @PostMapping("/view/usuarios/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        usuarioRepository.deleteById(id);
        ra.addFlashAttribute("message", "Usuario eliminado correctamente");
        return "redirect:/view/usuarios";
    }
}

