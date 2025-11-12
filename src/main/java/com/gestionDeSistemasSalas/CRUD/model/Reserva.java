package com.gestionDeSistemasSalas.CRUD.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reserva")
@Builder
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;

    @NotBlank(message = "Fecha de reserva es requerida")
    @Column(nullable = false)
    private String fecha_reserva;

    @NotBlank(message = "Hora de inicio es requerida")
    @Column(nullable = false)
    private String hora_inicio;

    @NotBlank(message = "Hora de fin es requerida")
    @Column(nullable = false)
    private String hora_fin;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala_id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario_id;
}
