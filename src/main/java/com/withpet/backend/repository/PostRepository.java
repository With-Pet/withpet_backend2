package com.withpet.backend.repository;

import com.withpet.backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post, Long> {

}
