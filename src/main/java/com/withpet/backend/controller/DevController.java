package com.withpet.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.withpet.backend.dto.ResultDto;
import com.withpet.backend.dto.ResultListDto;
import com.withpet.backend.entity.Fad;
import com.withpet.backend.entity.QandA;
import com.withpet.backend.entity.User;
import com.withpet.backend.entity.repository.FadRepository;
import com.withpet.backend.entity.repository.QARepository;
import com.withpet.backend.entity.repository.UserRepository;
import com.withpet.backend.jwt.JwtUtils;
import com.withpet.backend.service.DevService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(tags = {"Dev Controller"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dev")
public class DevController {

    private final FadRepository fadRepository;
    private final UserRepository userRepository;
    private final QARepository qaRepository;
    private final DevService devService;
    private final ResultDto resultDto = new ResultDto();
    private final ResultListDto resultListDto = new ResultListDto();

    @Autowired
    private JwtUtils jwtTokenProvider;

    /**
     * 모든 시설 데이터 반환
     */
    @GetMapping(value = "/GetFad")
    public ResponseEntity<List<Fad>> getMainData() {
        return ResponseEntity.status(HttpStatus.OK).body(devService.getMainPageAllData());
    }

    /**
     * 데이터 반환 연습
     */
    @GetMapping(value = "/GetPracticeData")
    public ResultDto getPracticeData() {
        resultListDto.setCode(HttpStatus.OK.value());
        resultListDto.setData(devService.getMainPageAllData());
        resultDto.setMessage(HttpStatus.OK.toString());
        return resultDto;
    }

    /**
     * 모든 질문 데이터 반환
     */
    @GetMapping(value = "/GetQA")
    public String getQAData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate5Module());
        List<QandA> a =  devService.getQA();
        String str = mapper.writeValueAsString(a);

        return str;
    }

    @GetMapping(value = "/GetAllQa")
    public List<QandA> getAllQa() {
        return qaRepository.findAll();
    }

    /**
     * 모든 회원 데이터 반환
     * @return
     */
    @GetMapping(value = "/GetAllUser")
    public List<User> getAllUser() {
        System.out.println(userRepository.findById(1));
        System.out.println(fadRepository.findById(7));
        return userRepository.findAll();
    }

    /**
     * access token 복호화
     */
    @GetMapping(value = "/GetUserName")
    public Object getUserName(@RequestParam String token) {
        return JwtUtils.getUsername(token);
    }
}
