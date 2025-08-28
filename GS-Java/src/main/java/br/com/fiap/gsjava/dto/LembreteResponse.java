package br.com.fiap.gsjava.dto;

import java.time.LocalDateTime;

public record LembreteResponse(Long idLembrete, String mensagem, LocalDateTime dataHora, Long idUsuario) {
}
