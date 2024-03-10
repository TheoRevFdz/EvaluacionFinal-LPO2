package com.hrevfdz.EvaluacionFinalLPO2.services;

import com.hrevfdz.EvaluacionFinalLPO2.models.Libro;

import java.util.List;
import java.util.Optional;

public interface ILibroService {
    Libro create(Libro libro);
    Libro update(Libro libro);

    List<Libro> findAll();

    Optional<Libro> findById(Long id);

    List<Libro> findByNombreAndGenero(String nombre, Long idGenero);
}
