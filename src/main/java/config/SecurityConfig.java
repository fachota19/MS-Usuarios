package ar.edu.utn.frc.backend.grupo114.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean Principal de Configuración de Seguridad.
     * Aquí definimos las REGLAS de acceso a los endpoints.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivamos CSRF porque usamos JWT (stateless)
                .csrf(AbstractHttpConfigurer::disable)

                // Definimos las reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        // Basado en tu PDF, todos los endpoints son solo para OPERADOR
                        .requestMatchers("/usuarios", "/usuarios/**").hasRole("OPERADOR")
                        .requestMatchers("/clientes", "/clientes/**").hasRole("OPERADOR")
                        .requestMatchers("/transportistas", "/transportistas/**").hasRole("OPERADOR")

                        // Cualquier otra petición (ej. /actuator/health)
                        // solo pedimos que esté autenticada.
                        .anyRequest().authenticated()
                )

                // Le decimos a Spring que esto es un Servidor de Recursos OAuth2
                // y que debe validar los tokens JWT.
                .oauth2ResourceServer(oauth2 -> oauth2
                        // Le decimos que use el "conversor" de JWT que definimos más abajo
                        // para que entienda los roles de Keycloak.
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )

                // Le decimos a Spring que NO cree sesiones. Cada petición debe
                // traer su propio token.
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    /**
     * Este Bean es CRUCIAL.
     * Le "enseña" a Spring Security cómo leer los roles
     * desde la estructura compleja de un token de Keycloak.
     *
     * Por defecto, Spring busca roles en un claim "scope", pero Keycloak
     * los pone dentro de "realm_access" -> "roles".
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Creamos un convertidor customizado para las "autoridades" (roles)
        Converter<Jwt, Collection<GrantedAuthority>> authoritiesConverter = jwt -> {

            // 1. Obtenemos el claim "realm_access" del token JWT
            final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaim("realm_access");

            if (realmAccess == null || realmAccess.isEmpty()) {
                return List.of(); // No hay roles
            }

            // 2. Obtenemos la lista de "roles" de adentro de "realm_access"
            final Collection<String> roles = (Collection<String>) realmAccess.get("roles");

            // 3. Mapeamos la lista de strings de roles (ej: "OPERADOR")
            //    a la clase que Spring Security entiende (SimpleGrantedAuthority)
            //    Añadimos el prefijo "ROLE_" que Spring espera.
            return roles.stream()
                    .map(roleName -> "ROLE_" + roleName) // Ej: "ROLE_OPERADOR"
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        };

        // Creamos el convertidor principal de JWT
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        // Le asignamos nuestro convertidor de roles customizado
        jwtConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return jwtConverter;
    }
}