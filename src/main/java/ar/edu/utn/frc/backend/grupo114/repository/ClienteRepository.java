package ar.edu.utn.frc.backend.grupo114.repository;

import ar.edu.utn.frc.backend.grupo114.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}