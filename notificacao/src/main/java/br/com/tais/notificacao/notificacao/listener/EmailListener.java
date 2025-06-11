package br.com.tais.notificacao.notificacao.listener;

import br.com.tais.notificacao.notificacao.entity.AgendaEntity;
import br.com.tais.notificacao.notificacao.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "agenda.pet-created")
    public void Listen(AgendaEntity agendaEntity) {
        emailService.sendEmail(agendaEntity);
        System.out.println("Email status: " + agendaEntity.toString());
    }
}