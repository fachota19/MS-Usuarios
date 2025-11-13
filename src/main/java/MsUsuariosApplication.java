package ar.edu.utn.frc.backend.grupo114;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta anotación es la magia de Spring Boot.
// Automáticamente configura, escanea y arranca todo.
@SpringBootApplication
public class MsUsuariosApplication {

    // Este es el método "main" estándar de Java que inicia la aplicación
    public static void main(String[] args) {
        SpringApplication.run(MsUsuariosApplication.class, args);
    }

}