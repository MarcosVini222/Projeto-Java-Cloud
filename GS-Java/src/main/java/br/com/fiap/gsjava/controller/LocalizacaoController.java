package br.com.fiap.gsjava.controller;


import br.com.fiap.gsjava.dto.LocalizacaoRequestDTO;
import br.com.fiap.gsjava.dto.LocalizacaoResponse;
import br.com.fiap.gsjava.service.NominatimService;
import br.com.fiap.gsjava.service.OverpassService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    private final NominatimService nominatimService;
    private final OverpassService overpassService;

    public LocalizacaoController(NominatimService nominatimService, OverpassService overpassService) {
        this.nominatimService = nominatimService;
        this.overpassService = overpassService;
    }

    // Salva localização com endereço do Nominatim
    @PostMapping("/salvar")
    public ResponseEntity<LocalizacaoResponse> salvarLocalizacao(@Valid @RequestBody LocalizacaoRequestDTO localizacaoRequest) {
        LocalizacaoResponse resposta = nominatimService.buscarEnderecoESalvar(localizacaoRequest);
        return ResponseEntity.ok(resposta);
    }

    // Busca lugares seguros próximos (escolas, delegacias, etc)
    @PostMapping("/lugares-seguros")
    public ResponseEntity<List<LocalizacaoResponse>> buscarLugaresSeguros(@RequestBody LocalizacaoRequestDTO localizacaoRequest) {
        List<LocalizacaoResponse> locais = overpassService.buscarLocaisSeguros(localizacaoRequest);
        return ResponseEntity.ok(locais);
    }
}