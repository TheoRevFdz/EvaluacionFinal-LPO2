package com.hrevfdz.EvaluacionFinalLPO2.services.impl;

import com.hrevfdz.EvaluacionFinalLPO2.dto.LibroDTO;
import com.hrevfdz.EvaluacionFinalLPO2.models.Libro;
import com.hrevfdz.EvaluacionFinalLPO2.repositories.ILibroRepository;
import com.hrevfdz.EvaluacionFinalLPO2.services.ILibroService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService implements ILibroService {
    @Autowired
    private ILibroRepository repository;

    @Override
    public Libro create(Libro libro) {
        libro.setFechaRegistro(LocalDate.now());
        if (libro.getFechaPublicacion() == null) libro.setFechaPublicacion(LocalDate.now());
        return repository.save(libro);
    }

    @Override
    public Libro update(Libro libro) {
        libro.setFechaRegistro(LocalDate.now());
        if (libro.getFechaPublicacion() == null) libro.setFechaPublicacion(LocalDate.now());
        return repository.findById(libro.getId())
                .map(l -> repository.save(libro))
                .orElseGet(() -> null);
    }

    @Override
    public List<Libro> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Libro> findByNombreAndGenero(String nombre, Long idGenero) {
        return repository.findByNombreAndGenero(nombre, idGenero);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public JasperPrint exportReport(String report) throws FileNotFoundException, JRException {
        List<Libro> listado = repository.findAll();
        List<LibroDTO> librosResult = listado.stream()
                .map(l -> {
                    return LibroDTO.builder()
                            .id(l.getId())
                            .author(l.getAuthor())
                            .genero(l.getGenero().getNombre())
                            .fechaPublicacion(l.getFechaPublicacion().toString())
                            .fechaRegistro(l.getFechaRegistro().toString())
                            .build();
                }).collect(Collectors.toList());
        File archivo = ResourceUtils.getFile(report);
        JasperReport jasperReport = JasperCompileManager.compileReport(archivo.getAbsolutePath());
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(librosResult);
        Map<String, Object> parametros = new HashMap<>();
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, ds);
        return print;
    }
}
