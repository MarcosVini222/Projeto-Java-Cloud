package br.com.fiap.gsjava.service;

import br.com.fiap.gsjava.dto.LocalizacaoRequestDTO;
import br.com.fiap.gsjava.dto.LocalizacaoResponse;
import br.com.fiap.gsjava.model.Localizacao;
import br.com.fiap.gsjava.repository.LocalizacaoRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class NominatimService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final LocalizacaoRepository localizacaoRepository;

    public NominatimService(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    public LocalizacaoResponse buscarEnderecoESalvar(LocalizacaoRequestDTO localizacaoRequest) {
        String url = String.format(
                "https://nominatim.openstreetmap.org/reverse?format=json&lat=%f&lon=%f&zoom=18&addressdetails=1",
                localizacaoRequest.latitude(), localizacaoRequest.longitude()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "SeuApp/1.0");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);
        Map<String, Object> responseBody = response.getBody();

        String endereco = "Endereço não encontrado";
        if (responseBody != null && responseBody.containsKey("display_name")) {
            endereco = (String) responseBody.get("display_name");
        }

        // Criação da Localizacao com id nulo e lista usuarios null
        Localizacao localizacao = new Localizacao();
        
        localizacao.setLatitude(localizacaoRequest.latitude());
        localizacao.setLongitude(localizacaoRequest.longitude());
        localizacao.setUsuarios(null);  // 
        localizacaoRepository.save(localizacao);

        return new LocalizacaoResponse(localizacao.getId(), localizacao.getLatitude(), localizacao.getLongitude(), endereco);
    }
}
