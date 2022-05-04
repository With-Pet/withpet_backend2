package com.withpet.backend.service;

import com.withpet.backend.domain.User;
import com.withpet.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 유저 등록
     *
     * @return 유저 권한을 가지고 있는 유저
     */
    @Transactional
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 패스워드 인코딩을 써서 암호화한다.
        userRepository.save(user);
        return user;
    }

    /**
     * 아이디, 비밀번호 올바른지 확인
     *
     * @param snsId   : 유저 아이디
     * @param usrPass : 유저 비밀번호
     * @return : 해당 유저 정보
     */
    public User checkLogIn(String snsId, String usrPass) {
        User user = userRepository.findBySnsId(snsId);

        if (!passwordEncoder.matches(usrPass, user.getPassword())) {
            throw new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}



