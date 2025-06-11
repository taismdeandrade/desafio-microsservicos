package br.com.tais.cadastro.repository;

import br.com.tais.cadastro.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findByPetRace(String petRace);

    List<PetEntity> findByPetSpecies(String petSpecies);

    Optional<PetEntity> findByPetId(Long id);
}
