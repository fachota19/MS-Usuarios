package ar.edu.utn.frc.backend.grupo114.controller;

import ar.edu.utn.frc.backend.grupo114.dto.*;
import ar.edu.utn.frc.backend.grupo114.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.edu.utn.frc.backend.grupo114.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // ============================
    // USUARIOS
    // ============================

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody CrearUsuarioRequestDTO request) {
        return ResponseEntity.ok(usuarioService.crearUsuario(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuario(id));
    }

    // ============================
    // CLIENTES
    // ============================

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody CrearClienteRequestDTO request) {
        return ResponseEntity.ok(usuarioService.crearCliente(request));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerCliente(id));
    }

    // ============================
    // TRANSPORTISTAS
    // ============================

    @PostMapping("/transportistas")
    public ResponseEntity<TransportistaDTO> crearTransportista(@RequestBody CrearTransportistaRequestDTO request) {
        return ResponseEntity.ok(usuarioService.crearTransportista(request));
    }

    @GetMapping("/transportistas/{id}")
    public ResponseEntity<TransportistaDTO> obtenerTransportista(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerTransportista(id));
    }
}
