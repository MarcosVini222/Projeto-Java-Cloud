package br.com.fiap.gsjava.repository;

import br.com.fiap.gsjava.model.Lembrete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
    List<Lembrete> findByUsuarioEmail(String email);
    Page<Lembrete> findByUsuario_Email(String email, Pageable pageable);
    Page<Lembrete> findAll(Pageable pageable);

}
