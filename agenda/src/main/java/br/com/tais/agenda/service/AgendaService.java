package br.com.tais.agenda.service;

import br.com.tais.agenda.dto.AgendaDTO;
import br.com.tais.agenda.entity.AgendaEntity;
import br.com.tais.agenda.entity.PetEntity;
import br.com.tais.agenda.repository.AgendaRepository;
import br.com.tais.agenda.repository.PetRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AgendaService {
    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private PetRepository petRepository;

    public List<AgendaEntity> listAll() {
        List<AgendaEntity> agendaEntityList = agendaRepository.findAll();
        return agendaEntityList.stream().toList();
    }

    public List<AgendaEntity> findByName(String petName) {
        List<AgendaEntity> agendaEntityList = agendaRepository.findByPetName(petName);
        return agendaEntityList.stream().toList();
    }

    public ResponseEntity newAppointment(PetEntity petEntity) {
        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setPetId(petEntity.getPetId());
        agendaEntity.setPetName(petEntity.getPetName());
        agendaEntity.setTutorName(petEntity.getTutorName());
        agendaEntity.setTutorEmail(petEntity.getTutorEmail());

        if (ChronoUnit.MONTHS.between(petEntity.getPetBirth(), LocalDate.now()) < 6) {
            agendaEntity.setAppointmentDate(LocalDate.now().plusDays(7));
            agendaEntity.setServiceType("Vacinas iniciais.");
        } else {
            agendaEntity.setAppointmentDate(LocalDate.now().plusDays(30));
            agendaEntity.setServiceType("Check-up.");
        }
        agendaRepository.save(agendaEntity);
        appointmentCreated(agendaEntity);
        return ResponseEntity.ok(agendaEntity);
    }

    public ResponseEntity newManualAppointment(AgendaDTO agendaDTO) {
        AgendaEntity agendaEntity = new AgendaEntity();
        PetEntity petEntity;
        Long id = agendaDTO.getPetId();
        agendaEntity.setPetId(id);

        if (petRepository.existsById(id)) {
            petEntity = petRepository.findById(id).get();
            agendaEntity.setPetName(petEntity.getPetName());
            agendaEntity.setTutorName(petEntity.getTutorName());
            agendaEntity.setTutorEmail(petEntity.getTutorEmail());

        } else {
            try {
                String uri = "http://localhost:8080/api/pets/{id}";
                RestTemplate restTemplate = new RestTemplate();
                petEntity = restTemplate.getForObject(uri, PetEntity.class);
            }catch (IllegalArgumentException e){
                return  ResponseEntity.status(400).body("Id não encontrado");
            }
        }
        agendaEntity.setServiceType(agendaDTO.getServiceType());
        agendaEntity.setAppointmentDate(agendaDTO.getAppointmentDate());
        agendaRepository.save(agendaEntity);
        appointmentCreated(agendaEntity);
        return ResponseEntity.ok("Cadastrado com sucesso.");
    }

    public void savePet(PetEntity petEntity) {
        petRepository.save(petEntity);
    }

    public void appointmentCreated(AgendaEntity agendaEntity){
        rabbitTemplate.convertAndSend(exchange, routingKey, agendaEntity);
    }

    public void deleteAppointment(Long id) {

        try {
            AgendaEntity agendaEntity = agendaRepository.findById(id).get();
            agendaRepository.delete(agendaEntity);

        } catch (Exception e) {
            throw new RuntimeException("Consulta não encontrada.");
        }
    }
}
