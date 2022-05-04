package com.withpet.backend.repository;

import com.withpet.backend.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository  extends JpaRepository<Pet, Long> {
    Optional<Pet> findById(Long id);
}
