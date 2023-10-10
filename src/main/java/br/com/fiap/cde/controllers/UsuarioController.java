package br.com.fiap.cde.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.cde.models.Credencial;
import br.com.fiap.cde.models.Usuario;
import br.com.fiap.cde.repository.UsuarioRepository;
import br.com.fiap.cde.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Usuario", description = "API de usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PagedResourcesAssembler<Object> assembler; 

    @Autowired
    TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody Credencial credencial){
        authenticationManager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }

    @PostMapping("registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        repository.save(usuario);
        return ResponseEntity.ok().build();
    }

}