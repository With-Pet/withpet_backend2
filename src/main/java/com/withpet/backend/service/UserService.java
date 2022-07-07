package com.withpet.backend.service;

import com.withpet.backend.domain.Certificate;
import com.withpet.backend.domain.User;
import com.withpet.backend.domain.UserFav;
import com.withpet.backend.dto.user.UpdateUserRequestDto;
import com.withpet.backend.jwt.JwtUtils;
import com.withpet.backend.repository.CertificateRepository;
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
    private final CertificateRepository certificateRepository;

    /**
     * 유저 프로필 반환
     */
    @Transactional
    public User getProfile(Long id) throws Exception {

//        String snsId = JwtUtils.getUsername(aToken);
        return userRepository.findById(id).orElseThrow(Exception::new);
    }

    /**
     * 찜내역(돌봄이) 반환
     */
    @Transactional
    public UserFav getUserFav(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(Exception::new);
        return
    }

    /**
     * 자격증 등록
     */
    @Transactional
    public Certificate Update(Certificate certificate) {
        return certificateRepository.save(certificate);
    }
//
//    /**
//     * 회원 수정
//     * 변경감지 적용
//     * Todo 이용 가능 서비스 수정 기능 구
//     */
//    @Transactional
//    public User updateProfile(UpdateUserRequestDto request) {
//        String snsId = JwtUtils.getUsername(request.getToken());
//        User user = userRepository.findBySnsId(snsId);
//        user.setName(request.getName());
//        user.setAddress(request.getAddress());
//        user.setIntroduction(request.getIntroduction());
//        return user;
//    }

    /**
     * 자격증 등록
     */
    @Transactional
    public Certificate registerCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }
}
