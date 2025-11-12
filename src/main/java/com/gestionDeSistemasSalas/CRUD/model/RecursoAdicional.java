package com.gestionDeSistemasSalas.CRUD.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "recursos_adicionales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecursoAdicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_recurso_adicional;

    @NotBlank(message = "Nombre es requerido")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "Descripción es requerida")
    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala_id;

}
