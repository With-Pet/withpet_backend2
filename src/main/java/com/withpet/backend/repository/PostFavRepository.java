package com.withpet.backend.repository;

import com.withpet.backend.domain.PostFav;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFavRepository extends JpaRepository<PostFav, Long> {
}
