package br.com.fiap.gsjava.dto;


import br.com.fiap.gsjava.model.UsuarioRole;

public record RegisterDTO(String email, String senha, String cpf, String nome, UsuarioRole role) {
}
