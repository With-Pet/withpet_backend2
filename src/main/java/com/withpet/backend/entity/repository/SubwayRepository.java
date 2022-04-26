package com.withpet.backend.entity.repository;

import com.withpet.backend.entity.Subway;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubwayRepository extends JpaRepository<Subway, Long> {

        List<Subway> findByStationNameContaining(String searchValue);



}