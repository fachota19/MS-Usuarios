package ar.edu.utn.frc.backend.grupo114.controller;

import ar.edu.utn.frc.backend.grupo114.dto.*;

import ar.edu.utn.frc.backend.grupo114.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UsuarioController {

    private final UsuarioService usuarioService;

    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    

    
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios); 
    }

    
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody CrearUsuarioRequestDTO request) {
        UsuarioDTO nuevoUsuario = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario); 
    }

    

    
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> getClientes() {
        List<ClienteDTO> clientes = usuarioService.getAllClientes();
        return ResponseEntity.ok(clientes); 
    }

    
    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody CrearClienteRequestDTO request) {
        ClienteDTO nuevoCliente = usuarioService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente); 
    }

    

    
    @GetMapping("/transportistas")
    public ResponseEntity<List<TransportistaDTO>> getTransportistas() {
        List<TransportistaDTO> transportistas = usuarioService.getAllTransportistas();
        return ResponseEntity.ok(transportistas); 
    }

    
    @PostMapping("/transportistas")
    public ResponseEntity<TransportistaDTO> crearTransportista(@RequestBody CrearTransportistaRequestDTO request) {
        TransportistaDTO nuevoTransportista = usuarioService.crearTransportista(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTransportista); 
    }
}