package br.com.tais.cadastro.service;

import br.com.tais.cadastro.dto.ExternalApiDto;
import br.com.tais.cadastro.dto.PetDTO;
import br.com.tais.cadastro.entity.PetEntity;
import br.com.tais.cadastro.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final Logger logger = LoggerFactory.getLogger(PetService.class);

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private PetRepository petRepository;

    private PetEntity petEntity;

    private ExternalApiService externalApiService;

    private ExternalApiDto externalApiDto;


    public PetService(RabbitTemplate rabbitTemplate, PetRepository petRepository, ExternalApiService externalApiService) {
        this.rabbitTemplate = rabbitTemplate;
        this.petRepository = petRepository;
        this.externalApiService = externalApiService;
    }

    public List<PetDTO> listAllPets() {
        List<PetEntity> petEntityList = petRepository.findAll();
        return petEntityList.stream().map(PetDTO::new).toList();
    }

    public Optional<PetEntity> listById(Long id) {
        return petRepository.findByPetId(id);
    }

    public ResponseEntity registerPet(PetDTO petDTO) {
        ModelMapper modelMapper = new ModelMapper();
        petEntity = modelMapper.map(petDTO, PetEntity.class);
        String race = petEntity.getPetRace();
        String url = "";
        String id = "";

        //Chama o metodo que recupera a url de imagem e guarda no extrenalApiDto
        getImage(race);

        //Pega a url que foi salva e seta no entity
        petEntity.setImgUrl(externalApiDto.getUrl());
        petRepository.save(petEntity);

       logger.info("Cadastro criado: {}", petEntity.toString());

        petCreated(petEntity);
        return ResponseEntity.ok("Cadastrado com sucesso.");

    }

    private void getImage(String race) {
        String url;
        String id;
        if (petEntity.getPetSpecies().equalsIgnoreCase("gato")) {
            url = "thecatapi";
            id = externalApiService.getExternalId("gato", race);
            if (id != null) {
                externalApiDto = externalApiService.getImages(url, id);
            } else {
                externalApiDto = new ExternalApiDto();
                externalApiDto.setUrl("no images found");
            }

        } else if (petEntity.getPetSpecies().equalsIgnoreCase("cachorro")) {
            url = "thedogapi";
            id = externalApiService.getExternalId("cachorro", race);
            if (id != null) {
                externalApiDto = externalApiService.getImages(url, id);
            } else {
                externalApiDto = new ExternalApiDto();
                externalApiDto.setUrl("no images found");
            }
        }
    }

    public List<PetDTO> listByRace(String race) {
        List<PetEntity> petEntityList = petRepository.findByPetRace(race);
        return petEntityList.stream().map(PetDTO::new).toList();
    }

    public List<PetDTO> listBySpecies(String species) {
        List<PetEntity> petEntityList = petRepository.findByPetSpecies(species);
        return petEntityList.stream().map(PetDTO::new).toList();
    }

    public ResponseEntity updatePet(Long id, PetDTO petDTO) {
        ModelMapper modelMapper = new ModelMapper();
        petEntity = modelMapper.map(petDTO, PetEntity.class);
        petEntity = petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nenhum pet encontrado."));
        petEntity.setPetName(petDTO.getPetName());
        petEntity.setPetSpecies(petDTO.getPetSpecies());
        petEntity.setTutorName(petDTO.getTutorName());
        petEntity.setTutorEmail(petDTO.getTutorEmail());
        petEntity.setPetRace(petDTO.getPetRace());
        petEntity.setPetBirth(petDTO.getPetBirth());
        petEntity.setPetWeight(petDTO.getPetWeight());
        petEntity.setPetColor(petDTO.getPetColor());
        getImage(petDTO.getPetRace());
        petEntity.setImgUrl(externalApiDto.getUrl());
        petEntity.setPetDescription(petDTO.getPetDescription());

        //Salva as modificações
        petRepository.save(petEntity);

        return ResponseEntity.ok(petDTO);
    }

    public void deletePet(Long id) {
        try {
            petEntity = petRepository.findById(id).get();
            petRepository.delete(petEntity);

        } catch (Exception e) {
            throw new RuntimeException("Pet não encontrado.");
        }
    }

    public void petCreated(PetEntity petEntity) {
        rabbitTemplate.convertAndSend(exchange, routingKey, petEntity);
    }
}
