package br.com.tais.cadastro.controller;

import br.com.tais.cadastro.dto.PetDTO;
import br.com.tais.cadastro.entity.PetEntity;
import br.com.tais.cadastro.service.ExternalApiService;
import br.com.tais.cadastro.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Cadastro", description = "Api para gerenciar o cadastro dos pets")
@RestController
@RequestMapping(value = "/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private ExternalApiService externalApiService;

    @Operation(summary = "Lista todos os pets cadastrados.", description = "Contém as operações para listar os dados de todos os pets cadastrados.",
            responses = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PetDTO[].class)))
    )
    @GetMapping
    public List<PetDTO> listAllPets() {
        return petService.listAllPets();
    }

    @Operation(summary = "Busca um pet por id.", description = "Contém as operações para buscar os dados de um pet cadastrado..",
            responses = @ApiResponse(responseCode = "201", description = "Busca realizada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PetEntity.class)))
    )
    @GetMapping("/{id}")
    public Optional<PetEntity> listById(@PathVariable Long id) {
        return petService.listById(id);
    }

    @Operation(summary = "Cadastra um novo pet.", description = "Contém as operações para cadastrar um novo pet.",
            responses = @ApiResponse(responseCode = "201", description = "Novo pet cadastrado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PetDTO.class)))
    )
    @PostMapping
    public ResponseEntity<PetDTO> registerPet(@RequestBody PetDTO petDTO) {
        petService.registerPet(petDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(petDTO);
    }

    @Operation(summary = "Filtra por raça.", description = "Contém as operações para filtrar a busca por raças.",
            responses = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    )
    @GetMapping(value = "/racas")
    public List<PetDTO> listByRace(@RequestParam String race) {
        return petService.listByRace(race);
    }

    @Operation(summary = "Filtra por espécie.", description = "Contém as operações para filtrar a busca por espécie.",
            responses = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    )
    @GetMapping(value = "/especies")
    public List<PetDTO> listBySpecies(@RequestParam String species) {
        return petService.listBySpecies(species);
    }

    @Operation(summary = "Atualizar os dados de um pet", description = "Contém as operações para atualizar os dados de um pet cadastrado.",
            responses = @ApiResponse(responseCode = "200", description = "Pet atualizado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PetDTO.class)))
    )
    @PutMapping("/{id}")
    public ResponseEntity updatePet(@PathVariable Long id, @RequestBody PetDTO petDTO) {
        return petService.updatePet(id, petDTO);
    }

    @Operation(summary = "Excluir o cadastro de um pet.", description = "Contém as operações para excluir os dados de um pet cadastrado.",
            responses = @ApiResponse(responseCode = "200", description = "Exclusão realizada com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Number.class)))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok("Pet removido com sucesso.");
    }
}
