package com.withpet.backend.entity.repository;

import com.withpet.backend.entity.MainPageRec;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MainRepository extends JpaRepository<MainPageRec, Long> {

    List<MainPageRec> findByPostType(String postType);

}
