package com.hrevfdz.EvaluacionFinalLPO2.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "libros")
public class Libro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String author;
    @Column(name = "fechaPublicacion")
    private LocalDate fechaPublicacion;
    @ManyToOne
    @JoinColumn(name = "idGenero")
    private Genero genero;
}
