package com.hrevfdz.EvaluacionFinalLPO2.repositories;

import com.hrevfdz.EvaluacionFinalLPO2.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {
    @Query(value = "SELECT * FROM libros l WHERE l.idgenero = :idGenero AND l.nombre LIKE LOWER(CONCAT('%', :nombre, '%'))", nativeQuery = true)
    List<Libro> findByNombreAndGenero(@Param("nombre") String nombre, @Param("idGenero") Long idGenero);
}
