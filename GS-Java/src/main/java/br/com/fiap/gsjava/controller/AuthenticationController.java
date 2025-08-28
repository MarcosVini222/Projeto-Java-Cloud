package br.com.fiap.gsjava.controller;

import br.com.fiap.gsjava.dto.AuthenticationDTO;
import br.com.fiap.gsjava.dto.LoginResponseDTO;
import br.com.fiap.gsjava.dto.RegisterDTO;
import br.com.fiap.gsjava.model.Usuario;
import br.com.fiap.gsjava.repository.UsuarioRepository;
import br.com.fiap.gsjava.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return  ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByEmail(data.email()) != null)return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario novoUsuario = new Usuario(data.email(), encryptedPassword, data.cpf(), data.nome(), data.role());

        this.repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
