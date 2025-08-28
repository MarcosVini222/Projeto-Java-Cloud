package br.com.fiap.gsjava.dto;

import java.time.LocalDateTime;

public record LembreteResponseDTO(Long idLembrete, String mensagem, LocalDateTime dataHora, Long idUsuario) {
}
