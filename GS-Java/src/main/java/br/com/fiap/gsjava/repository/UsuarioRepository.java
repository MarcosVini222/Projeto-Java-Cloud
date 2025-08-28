package br.com.fiap.gsjava.repository;

import br.com.fiap.gsjava.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
    Optional<Usuario> findOptionalByEmail(String email);
}
