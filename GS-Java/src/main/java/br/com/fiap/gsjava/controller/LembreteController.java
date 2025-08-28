package br.com.fiap.gsjava.controller;



import br.com.fiap.gsjava.dto.LembreteRequest;
import br.com.fiap.gsjava.dto.LembreteRequestDTO;
import br.com.fiap.gsjava.dto.LembreteResponse;
import br.com.fiap.gsjava.dto.LembreteResponseDTO;
import br.com.fiap.gsjava.service.LembreteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lembretes")
public class LembreteController {

    private final LembreteService lembreteService;

    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    //CRIAR LEMBRETE
    @PostMapping()
    public ResponseEntity<LembreteResponse> post( @Valid @RequestBody LembreteRequestDTO lembreteRequestDTO) {
        LembreteResponse response = lembreteService.criarLembrete(lembreteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    //DELETAR LEMBRETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        lembreteService.deletarLembrete(id);
    }


    //ATUALIZAR LEMBRETE
    @PutMapping("/atualizar")
    public LembreteResponse atualizarLembrete(@RequestBody LembreteRequest request) {
        return lembreteService.atualizarPorEmailELembreteId(request);
    }
    //BUSCANDO LEMBRETES POR EMAIL
    @GetMapping("/usuario/{email}")
    public List<LembreteResponse> getLembretesPorEmail(@PathVariable String email) {
        return lembreteService.buscarLembretesPorEmail(email);
    }

    //paginação de lembretes por id do usuario
    @GetMapping("/paginado")
    public ResponseEntity<Page<LembreteResponseDTO>> listarTodosLembretesPaginado(
            @PageableDefault(page = 0, size = 10, sort = "dataHora,desc") Pageable pageable) {
        // Você precisará de um método no seu serviço que liste todos os lembretes paginados
        Page<LembreteResponseDTO> lembretePage = lembreteService.listarTodosLembretesPaginado(pageable);
        return ResponseEntity.ok(lembretePage);
    }

    @PutMapping("/atualizar/email")
    public ResponseEntity<LembreteResponseDTO> atualizarEmailLembrete(@RequestBody LembreteRequest request) {
        try {
            LembreteResponseDTO atualizado = lembreteService.atualizarEmailLembrete(request);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
