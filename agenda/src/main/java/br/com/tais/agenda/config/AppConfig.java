package br.com.tais.agenda.config;

import br.com.tais.agenda.controller.AgendaController;
import br.com.tais.agenda.entity.AgendaEntity;
import br.com.tais.agenda.listener.AgendaListener;
import br.com.tais.agenda.service.AgendaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AgendaListener agendaListener() {
        return new AgendaListener();
    }
}
