package com.withpet.backend.service;

import com.withpet.backend.domain.User;
import com.withpet.backend.dto.user.UpdateUserRequestDto;
import com.withpet.backend.jwt.JwtUtils;
import com.withpet.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    /**
     * 유저 프로필 반환
     * @return
     */
    @Transactional
    public User getProfile(String aToken) {
        String snsId = JwtUtils.getUsername(aToken);
        return userRepository.findBySnsId(snsId);
    }

    /**
     * 회원 수정
     * 변경감지 적용
     */
    @Transactional
    public User updateProfile(UpdateUserRequestDto request) {
        String snsId = JwtUtils.getUsername(request.getToken());
        User user = userRepository.findBySnsId(snsId);
        user.setName(request.getName());
        user.setAddress(request.getAddress());
        user.setIntroduction(request.getIntroduction());
        return user;
    }
}
