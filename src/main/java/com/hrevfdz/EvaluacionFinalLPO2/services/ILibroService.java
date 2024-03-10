package com.hrevfdz.EvaluacionFinalLPO2.services;

import com.hrevfdz.EvaluacionFinalLPO2.models.Libro;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface ILibroService {
    Libro create(Libro libro);
    Libro update(Libro libro);

    List<Libro> findAll();

    Optional<Libro> findById(Long id);

    List<Libro> findByNombreAndGenero(String nombre, Long idGenero);

    void delete(Long id);

    JasperPrint exportReport(String report) throws FileNotFoundException, JRException;
}
