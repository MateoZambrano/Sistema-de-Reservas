package com.gestionDeSistemasSalas.CRUD.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @NotBlank(message = "Nombre es requerido")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "Correo es requerido")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "Tipo de usuario es requerido")
    @Column(nullable = false)
    private String tipo_usuario;
}
