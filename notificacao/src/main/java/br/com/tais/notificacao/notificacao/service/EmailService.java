package br.com.tais.notificacao.notificacao.service;

import br.com.tais.notificacao.notificacao.entity.AgendaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;


    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(AgendaEntity agendaEntity){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setSubject("Agendamento de cuidados");
        message.setTo(agendaEntity.getTutorEmail());
        message.setText("Olá " + agendaEntity.getTutorName() + "," +" o agendamento de " + agendaEntity.getServiceType() + " para seu pet " + agendaEntity.getPetName() + " foi marcado para " + agendaEntity.getAppointmentDate());
        log.info("Email enviado.");
        mailSender.send(message);
    }
}
