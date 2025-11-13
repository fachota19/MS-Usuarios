package ar.edu.utn.frc.backend.grupo114.repository;

import ar.edu.utn.frc.backend.grupo114.model.TipoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoRolRepository extends JpaRepository<TipoRol, Long> {

    // Este método es crucial. Spring lo crea solo por el nombre.
    // Lo usaremos para buscar el rol "CLIENTE" o "OPERADOR"
    // antes de crear un nuevo usuario.
    Optional<TipoRol> findByNombre(String nombre);
}