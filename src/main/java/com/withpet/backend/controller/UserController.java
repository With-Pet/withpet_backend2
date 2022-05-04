package com.withpet.backend.controller;

import com.withpet.backend.domain.Certificate;
import com.withpet.backend.domain.User;
import com.withpet.backend.dto.result.SingleResult;
import com.withpet.backend.dto.user.*;
import com.withpet.backend.repository.UserRepository;
import com.withpet.backend.service.ResponseService;
import com.withpet.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final ResponseService responseService;
    private final UserService userService;

    /**
     * 1.6 본인 프로필 반환
     */
    @ApiOperation(value = "본인 프로필을 반환", notes = "본인 프로필을 반환한다.")
    @GetMapping(value = "/getUserProfile")
    public SingleResult<GetUserProfileResponseDto> getUserProfile(@RequestParam String aToken) {
        User user = userService.getProfile(aToken);
        return responseService.getSingleResult(new GetUserProfileResponseDto(user.getName(), user.getAddress(), user.getIntroduction()));
    }

    /**
     * 1.7 본인 프로필 수정
     */
    @ApiOperation(value = "본인 프로필 수정", notes = "본인 프로필을 수정한다.")
    @PatchMapping(value = "/updateUserProfile")
    public SingleResult<UpdateUserResponseDto> updateUserProfile(@RequestBody @Valid UpdateUserRequestDto request) {

        User updatedUser = userService.updateProfile(request);
        return responseService.getSingleResult(new UpdateUserResponseDto(updatedUser.getName(), updatedUser.getAddress(), updatedUser.getIntroduction()));
    }

    /**
     * 2.8 프로필 자격증 등록
     */
    @ApiOperation(value = "프로필 자격증 등록", notes = "본인의 자격증을 등록한다.")
    @PostMapping(value = "/registerCertificate")
    public SingleResult<RegisterCerResponseDto> registerCertificate(@RequestBody @Valid RegisterCerRequestDto request) throws Exception {
        User user = userRepository.findById(request.getId()).orElseThrow(Exception::new);
        Certificate certificate = Certificate.createCer(user, request.getName(), request.getAgency(), request.getDate());
        userService.registerCertificate(certificate);
        return responseService.getSingleResult(new RegisterCerResponseDto(request.getName(), request.getAgency(), request.getDate()));
    }
}
