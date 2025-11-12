package com.gestionDeSistemasSalas.CRUD.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "sala")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sala;

    @NotBlank(message = "Nombre es requerido")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "Capacidad es requerida")
    @Column(nullable = false)
    private int capacidad;

    @NotBlank(message = "Ubicacion es requerida")
    @Column(nullable = false)
    private String ubicacion;

    @NotBlank(message = "Estado es requerido")
    @Column(nullable = false)
    private String estado;

}
