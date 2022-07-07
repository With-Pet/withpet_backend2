package com.withpet.backend.repository;

import com.withpet.backend.domain.UserFav;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavRepository extends JpaRepository<UserFav, Long> {
}
