package com.hrevfdz.EvaluacionFinalLPO2.repositories;

import com.hrevfdz.EvaluacionFinalLPO2.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Long> {
}
