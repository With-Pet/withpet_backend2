package com.withpet.backend.service;

import com.withpet.backend.entity.Fad;
import com.withpet.backend.entity.QandA;
import com.withpet.backend.entity.repository.FadRepository;
import com.withpet.backend.entity.repository.QARepository;
import com.withpet.backend.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DevService {

    private final FadRepository fadRepository;
    private final UserRepository userRepository;
    private final QARepository qaRepository;


    /**
     * 시설 모든 데이터 반환
     * @return 모든 데이터 반환
     */
    @Transactional
    public List<Fad> getMainPageAllData() {
        return fadRepository.findAll();
    }

    /**
     * QA 모든 데이터 반환
     * @return 모든 데이터 반환
     */
    @Transactional
    public List<QandA> getQA() {
        return qaRepository.findAll();
    }
}
