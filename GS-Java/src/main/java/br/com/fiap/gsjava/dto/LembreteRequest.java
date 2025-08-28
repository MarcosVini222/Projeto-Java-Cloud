package br.com.fiap.gsjava.dto;

import java.time.LocalDateTime;

public record LembreteRequest (Long idLembrete,String mensagem, LocalDateTime dataHora, String emailUsuario) {
}
