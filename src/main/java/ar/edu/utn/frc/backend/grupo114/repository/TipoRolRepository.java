package ar.edu.utn.frc.backend.grupo114.repository;

import ar.edu.utn.frc.backend.grupo114.model.TipoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoRolRepository extends JpaRepository<TipoRol, Long> {

    
    
    
    Optional<TipoRol> findByNombre(String nombre);
}