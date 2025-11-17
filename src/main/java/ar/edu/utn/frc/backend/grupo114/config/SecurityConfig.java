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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                // --- INICIO DEL ARREGLO ---
                .authorizeHttpRequests(auth -> auth
                        // Cambiamos .hasRole("OPERADOR") por .hasAuthority("ROLE_OPERADOR")
                        // para que coincida con el prefijo que agregamos en el conversor de abajo.
                        .requestMatchers("/usuarios", "/usuarios/**").hasAuthority("ROLE_OPERADOR")
                        .requestMatchers("/clientes", "/clientes/**").hasAuthority("ROLE_OPERADOR")
                        .requestMatchers("/transportistas", "/transportistas/**").hasAuthority("ROLE_OPERADOR")

                        .anyRequest().authenticated()
                )
                // --- FIN DEL ARREGLO ---

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        Converter<Jwt, Collection<GrantedAuthority>> authoritiesConverter = jwt -> {

            final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaim("realm_access");

            if (realmAccess == null || realmAccess.isEmpty()) {
                return List.of();
            }

            final Collection<String> roles = (Collection<String>) realmAccess.get("roles");

            // Aquí es donde agregamos el prefijo "ROLE_"
            return roles.stream()
                    .map(roleName -> "ROLE_" + roleName) // Ej: "ROLE_OPERADOR"
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        };

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return jwtConverter;
    }
}