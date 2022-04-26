package com.withpet.backend.entity.repository;

import com.withpet.backend.entity.QandA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QARepository extends JpaRepository<QandA, Long> {

    public Optional<QandA> findById(int id);



}

