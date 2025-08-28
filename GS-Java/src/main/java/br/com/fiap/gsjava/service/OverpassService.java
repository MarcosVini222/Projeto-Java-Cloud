package br.com.fiap.gsjava.service;

import br.com.fiap.gsjava.dto.LocalizacaoRequestDTO;
import br.com.fiap.gsjava.dto.LocalizacaoResponse;
import br.com.fiap.gsjava.model.LugarSeguro;
import br.com.fiap.gsjava.repository.LugarSeguroRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class OverpassService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final LugarSeguroRepository lugarSeguroRepository;

    public OverpassService(LugarSeguroRepository lugarSeguroRepository) {
        this.lugarSeguroRepository = lugarSeguroRepository;
    }

    public List<LocalizacaoResponse> buscarLocaisSeguros(LocalizacaoRequestDTO localizacaoRequest) {
        double latitude = localizacaoRequest.latitude();
        double longitude = localizacaoRequest.longitude();

        // Verificação adicional para garantir que latitude e longitude estejam dentro do intervalo correto
        if (latitude < -90.0 || latitude > 90.0 || longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90, and Longitude between -180 and 180.");
        }

        String query = String.format(Locale.US, // Adicionado Locale.US aqui!
                """
                [out:json];
                (
                  node["amenity"~"school|police"](around:2000, %f, %f);
                );
                out body;
                """, latitude, longitude);

        String url = "https://overpass-api.de/api/interpreter";

        HttpHeaders headers = new HttpHeaders();
        // Mude para MediaType.TEXT_PLAIN ou MediaType.APPLICATION_JSON (se a query fosse JSON)
        headers.setContentType(MediaType.TEXT_PLAIN); // Alterado aqui!

        // O corpo da requisição deve ser a própria query, sem "data="
        HttpEntity<String> request = new HttpEntity<>(query, headers); // Alterado aqui!

        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
            Map<String, Object> responseBody = response.getBody();

            List<LocalizacaoResponse> dtos = new ArrayList<>();

            if (responseBody != null && responseBody.containsKey("elements")) {
                List<Map<String, Object>> elementos = (List<Map<String, Object>>) responseBody.get("elements");

                for (Map<String, Object> elemento : elementos) {
                    Double lat = (Double) elemento.get("lat");
                    Double lon = (Double) elemento.get("lon");
                    Map<String, Object> tags = (Map<String, Object>) elemento.get("tags");
                    String nome = tags != null ? (String) tags.getOrDefault("name", "Local seguro") : "Local seguro";

                    // Cria o LugarSeguro e salva no banco
                    LugarSeguro lugar = new LugarSeguro(nome, lat, lon);
                    lugar = lugarSeguroRepository.save(lugar);

                    // Adiciona na lista de DTOs com id do banco
                    dtos.add(new LocalizacaoResponse(lugar.getId(), lat, lon, nome));
                }
            }
            return dtos;
        } catch (HttpClientErrorException e) {
            // Imprime o corpo da resposta de erro para depuração
            System.err.println("Erro ao chamar Overpass API: " + e.getResponseBodyAsString());
            throw e; // Re-lança a exceção para que o controller possa tratá-la
        }
    }
}