package br.com.tais.agenda.listener;

import br.com.tais.agenda.entity.PetEntity;
import br.com.tais.agenda.service.AgendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AgendaListener {

    private final Logger logger = LoggerFactory.getLogger(AgendaListener.class);

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Autowired
    private AgendaService agendaService;

    public AgendaListener() {
    }

    @RabbitListener(queues = "cadastro.pet-created")
    public void listen(PetEntity petEntity) {
        try {
            logger.info("Novo pet: {}", petEntity.getPetId());
            agendaService.savePet(petEntity);
            agendaService.newAppointment(petEntity);
        } catch (Exception message) {
            logger.error(("Erro ao processar:" + message));
        }
    }

}
