package br.com.tais.cadastro.entity;

import br.com.tais.cadastro.dto.PetDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pets")
public class PetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;

    private String petName;
    private String petSpecies;
    private String tutorName;
    private String tutorEmail;
    private String petRace;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate petBirth;
    private Double petWeight;
    private String petColor;
    private String petDescription;
    private String imgUrl;

    public PetEntity() {

    }

    public PetEntity(PetDTO petDto) {

        this.petName = petDto.getPetName();
        this.petSpecies = petDto.getPetSpecies();
        this.tutorName = petDto.getTutorName();
        this.tutorEmail = petDto.getTutorEmail();
        this.petRace = petDto.getPetRace();
        this.petBirth = petDto.getPetBirth();
        this.petWeight = petDto.getPetWeight();
        this.petDescription = petDto.getPetDescription();
        this.imgUrl = petDto.getImgUrl();
    }

}
