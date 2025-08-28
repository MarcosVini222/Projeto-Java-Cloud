package br.com.fiap.gsjava.dto;

import jakarta.validation.constraints.NotNull;

public record LocalizacaoRequestDTO (@NotNull(message = "A longitude é obrigatória.") // Adicione @NotNull
                                     Double longitude,

                                     @NotNull(message = "A latitude é obrigatória.") // Adicione @NotNull
                                     Double latitude) {
}
