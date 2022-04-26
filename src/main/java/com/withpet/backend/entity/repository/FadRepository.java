package com.withpet.backend.entity.repository;

import com.withpet.backend.entity.Fad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FadRepository extends JpaRepository<Fad, Long> {

       public Optional<Fad> findById(int id);

       public List<Fad> findByFadNameContaining(String searchValue);

       public Fad findByFadName(String fadName);


}
