package br.com.fiap.gsjava.dto;

import jakarta.validation.constraints.Future;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class LembreteRequestDTO {
    @NotBlank(message = "A mensagem é obrigatória")
    private String mensagem;
    @NotNull(message = "A data e a hora dos lembretes são obrigatórios, e devem seguir o formato 2025-06-07T15:30:00")
    @Future
    private LocalDateTime dataHora;

    public LembreteRequestDTO(String mensagem, LocalDateTime dataHora) {
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public LembreteRequestDTO() {

    }
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
