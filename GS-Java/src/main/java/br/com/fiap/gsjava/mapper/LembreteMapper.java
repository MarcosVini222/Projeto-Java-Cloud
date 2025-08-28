package br.com.fiap.gsjava.mapper;


import br.com.fiap.gsjava.dto.LembreteRequestDTO;
import br.com.fiap.gsjava.dto.LembreteResponseDTO;
import br.com.fiap.gsjava.model.Lembrete;
import br.com.fiap.gsjava.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class LembreteMapper {
    public LembreteResponseDTO toResponseDTO(Lembrete lembrete) {
        if (lembrete == null) {
            return null;
        }
        return new LembreteResponseDTO(
                lembrete.getId(),
                lembrete.getMensagem(),
                lembrete.getDataHora(),
                lembrete.getUsuario() != null ? lembrete.getUsuario().getId() : null
        );
    }

    public Lembrete toEntity(LembreteRequestDTO dto, Long usuarioId) {
        if (dto == null) {
            return null;
        }
        Lembrete lembrete = new Lembrete();
        lembrete.setMensagem(dto.getMensagem());
        lembrete.setDataHora(dto.getDataHora());
        if (usuarioId != null) {
            Usuario usuario = new Usuario();
            usuario.setId(usuarioId);
            lembrete.setUsuario(usuario);
        }
        return lembrete;
    }
}
