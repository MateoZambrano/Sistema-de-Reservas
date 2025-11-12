package com.gestionDeSistemasSalas.CRUD.repository;

import com.gestionDeSistemasSalas.CRUD.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{

}
