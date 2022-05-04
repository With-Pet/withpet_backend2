package com.withpet.backend.controller;

import com.withpet.backend.domain.Service;
import com.withpet.backend.domain.User;
import com.withpet.backend.dto.result.SingleResult;
import com.withpet.backend.dto.user.*;
import com.withpet.backend.jwt.JwtUtils;
import com.withpet.backend.repository.ServiceRepository;
import com.withpet.backend.service.ResponseService;
import com.withpet.backend.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Api(tags = {"1. Managing User Authentication "})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
@Validated
public class SignController {

    @Autowired
    private SignService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenProvider;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private ServiceRepository serviceRepository;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * 1.1 회원가입 api
     * <p>
     * Entity 로 받을 시
     * 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
     * 엔티티에 API 검증을 위한 로직이 들어간다. (@NotEmpty 등등)
     * 실무에서는 회원 엔티티를 위한 API가 다양하게 만들어지는데, 한 엔티티에 각각의 API를 위한 모든 요청 요구사항을 담기는 어렵다.
     * 엔티티가 변경되면 API 스펙이 변한다.
     * 결론 : API 요청 스펙에 맞추어 별도의 DTO를 파라미터로 받는다.
     */
    @ApiOperation(value = "1.1 회원가입", notes = "회원 가입을 한다.(임시 - 카카오 로그인으로 추후 변경)")
    @PostMapping(value = "/Join")
    public SingleResult<UserResponseDto> join(
            @RequestBody @Valid UserRequestDto request
    ) {

        User user = User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .snsId(request.getSnsId())
                .provider(request.getProvider())
                .address(request.getAddress())
                .x(request.getX())
                .y(request.getY())
                .introduction(request.getIntroduction())
                .build();

        User registeredUser = userService.saveUser(user);
        registeredUser.setServices(addService(registeredUser));

        return responseService.getSingleResult(new UserResponseDto(registeredUser.getId(), registeredUser.getName(), registeredUser.getSnsId(), registeredUser.getX(), registeredUser.getY(), registeredUser.getCreatedAt(), registeredUser.getServices()));
    }

    private ArrayList<Service> addService(User user){
        ArrayList<Service> services = new ArrayList<>();
        Map<String,Boolean> map1 = new HashMap<>();
        map1.put("목욕", false);
        map1.put("마당 보유", false);
        map1.put("집 앞 픽업", false);
        map1.put("실내 놀이", false);
        map1.put("미용", false);
        map1.put("모발 관리", false);
        map1.put("노견 케어", false);
        map1.put("퍼피 케어", false);
        map1.put("등산", false);
        map1.put("소독", false);
        Service service = Service.createService(user,map1);
        serviceRepository.save(service);
        services.add(service);
        return services;
    }

    /**
     * 1.2 로컬 로그인 api
     * 응답 값으로 엔티티를 직접 외부에 노출할 시
     * 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
     * 기본적으로 엔티티의 모든 값이 노출된다.
     * 응답 스펙을 맞추기 위해 로직이 추가된다. (@JsonIgnore, 별도의 뷰 로직 등등)
     * 실무에서는 같은 엔티티에 대해 API가 용도에 따라 다양하게 만들어지는데, 한 엔티티에 각각의 API를 위한 프레젠테이션 응답 로직을 담기는 어렵다.
     * 엔티티가 변경되면 API 스펙이 변한다.
     * 추가로 컬렉션을 직접 반환하면 항후 API 스펙을 변경하기 어렵다.(별도의 Result 클래스 생성으로 해결)
     * 결론 : API 응답 스펙에 맞추어 별도의 DTO를 반환한다.
     */
    @ApiOperation(value = "1.2 로컬 로그인", notes = "로컬 회원 로그인을 시도한다")
    @PostMapping(value = "/Login")
    public SingleResult<AuthResponseDto> login(@RequestBody @Valid
                                                       LoginRequestDto loginReq) {

        //계정 존재 여부 체크 후 객체 생성, 추후 임시 비밀번호 여부 체크 시 사용
        User user = userService.checkLogIn(loginReq.getSnsId(), loginReq.getPassword());

        //아이디와 패스워드로, Security 가 알아 볼 수 있는 token 객체로 변경한다.
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginReq.getSnsId(), loginReq.getPassword());

        //AuthenticationManager 에 token 을 넘기면 UserDetailsService 가 받아 처리하도록 한다.
        Authentication authentication = authenticationManager.authenticate(token);

        //실제 SecurityContext 에 authentication 정보를 등록한다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Authentication 객체의 getPrincipal() 메서드를 실행하게 되면, UserDetails를 구현한 사용자 객체를 Return 한다.
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        /* access 토큰과 refresh 토큰을 발급 */
        String accessToken = jwtTokenProvider.generateAccessToken(userPrincipal);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userPrincipal);

        return responseService.getSingleResult(new AuthResponseDto(accessToken, refreshToken));
    }
}
