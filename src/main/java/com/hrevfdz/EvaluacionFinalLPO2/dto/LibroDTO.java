package com.hrevfdz.EvaluacionFinalLPO2.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibroDTO {
    private Long id;
    private String nombre;
    private String author;
    private String genero;
    private String fechaPublicacion;
    private String fechaRegistro;
}
