package com.hrevfdz.EvaluacionFinalLPO2.services.impl;

import com.hrevfdz.EvaluacionFinalLPO2.models.Genero;
import com.hrevfdz.EvaluacionFinalLPO2.repositories.IGeneroRepository;
import com.hrevfdz.EvaluacionFinalLPO2.services.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService implements IGeneroService {
    @Autowired
    private IGeneroRepository repository;

    @Override
    public Genero create(Genero genero) {
        return repository.save(genero);
    }

    @Override
    public Genero update(Genero genero) {
        return repository.findById(genero.getId())
                .map(g -> repository.save(genero))
                .orElseGet(() -> null);
    }

    @Override
    public List<Genero> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Genero> gindById(Long id) {
        return repository.findById(id);
    }
}
