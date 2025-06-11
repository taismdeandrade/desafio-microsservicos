package br.com.tais.cadastro.service;

import br.com.tais.cadastro.dto.ExternalApiDto;
import br.com.tais.cadastro.dto.ExternalPetsDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalApiService {


    private RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getExternalId(String species, String raceName) {
        String apiUrl = "";
        // Verifica qual a especie e então chama a url correspondente
        if (species.equalsIgnoreCase("cachorro")) {
            apiUrl = "thedogapi";
        } else if (species.equalsIgnoreCase("gato")) {
            apiUrl = "thecatapi";
        }
        String URI = String.format("https://api.%s.com/v1/breeds/search?name=%s", apiUrl, raceName);

        // Como o JSON retorna uma lista de objetos, preciso desserializar a resposta como uma lista de ExternalPetsDto
        ResponseEntity<List<ExternalPetsDto>> response = restTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ExternalPetsDto>>() {
                }
        );

        // Coloca os objetos em uma lista
        List<ExternalPetsDto> pets = response.getBody();
        // Verifica se a lista é válida e então retorna o id do primeiro objeto
        if (pets != null && !pets.isEmpty()) {
            return pets.get(0).getId();
        } else {
            return null;//Caso a lista esteja vazia, retorna nulo
        }
    }

    // Busca as imagens na API externa, mudando o url de acordo com a especie
    public ExternalApiDto getImages(String url, String id) {
        String URI = String.format("https://api.%s.com/v1/images/search?breed_ids=%s&api_key=API_KEY", url, id);

        ResponseEntity<List<ExternalApiDto>> response = restTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        List<ExternalApiDto> images = response.getBody();

        if (images != null && !images.isEmpty()) {
            return images.get(0);
        } else {
            return null;
        }
    }
}
