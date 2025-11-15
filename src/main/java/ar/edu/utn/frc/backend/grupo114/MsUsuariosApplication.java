package ar.edu.utn.frc.backend.grupo114;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;


@SpringBootApplication(exclude = { R2dbcAutoConfiguration.class })
public class MsUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUsuariosApplication.class, args);
    }

}