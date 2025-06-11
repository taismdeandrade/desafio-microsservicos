package br.com.tais.agenda.controller;

import br.com.tais.agenda.dto.AgendaDTO;
import br.com.tais.agenda.entity.AgendaEntity;
import br.com.tais.agenda.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Operation(summary = "Lista todos os agendamentos cadastrados.", description = "Contém as operações para buscar todos os agendamentos.",
            responses = @ApiResponse(responseCode = "201", description = "Busca realizada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendaEntity[].class)))
    )
    @GetMapping
    public List<AgendaEntity> listAll() {
        return agendaService.listAll();
    }
    @Operation(summary = "Filtra os agendamentos por nome do pet", description = "Contém as operações para filtrar os agendamentos por nome do pet.",
            responses = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendaEntity[].class)))
    )
    @GetMapping(value = "/nome")
    public List<AgendaEntity> findByName(@RequestParam String petName) {
        return agendaService.findByName(petName);
    }

    @Operation(summary = "Cria um agendamento manual.", description = "Contém as operações para realizar agendamentos manuais.",
            responses = @ApiResponse(responseCode = "201", description = "Novo agendamento realizado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendaDTO.class)))
    )
    @PostMapping
    public ResponseEntity newManualAppointment(@RequestBody AgendaDTO agendaDTO) {
        agendaService.newManualAppointment(agendaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaDTO);
    }

    @Operation(summary = "Remove um agendamento.", description = "Contém as operações para remover agendamentos.",
            responses = @ApiResponse(responseCode = "201", description = "Agendamento removido com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendaDTO.class)))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAppointment(@PathVariable Long id) {
        agendaService.deleteAppointment(id);
        return ResponseEntity.ok("Consulta removida com sucesso.");
    }
}
