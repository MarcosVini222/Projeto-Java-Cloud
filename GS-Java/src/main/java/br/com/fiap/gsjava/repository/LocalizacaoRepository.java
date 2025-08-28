package br.com.fiap.gsjava.repository;

import br.com.fiap.gsjava.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
}
