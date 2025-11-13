package ar.edu.utn.frc.backend.grupo114.repository;

import ar.edu.utn.frc.backend.grupo114.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Por ahora no necesitamos métodos custom aquí
}