package com.withpet.backend.service;

import com.withpet.backend.domain.User;
import com.withpet.backend.dto.user.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.withpet.backend.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Spring Security가 User 클래스를 사용해 Authentication을 사용
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usrId) throws UsernameNotFoundException {
        // User정보를 DB에서 가져온다
        User user = userRepository.findBySnsId(usrId);
        return UserPrincipal.create(user);
    }
}