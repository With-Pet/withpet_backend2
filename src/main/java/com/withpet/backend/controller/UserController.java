package com.withpet.backend.controller;

import com.withpet.backend.domain.Certificate;
import com.withpet.backend.domain.User;
import com.withpet.backend.dto.result.SingleResult;
import com.withpet.backend.dto.user.*;
import com.withpet.backend.repository.PostFavRepository;
import com.withpet.backend.repository.UserFavRepository;
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
    private final PostFavRepository postFavRepository;
    private final UserFavRepository userFavRepository;

    /**
     * 1.5 본인 프로필 반환
     */
    @ApiOperation(value = "본인 프로필을 반환", notes = "본인 프로필을 반환한다.")
    @GetMapping(value = "/getUserProfile")
    public SingleResult<GetUserProfileResponseDto> getUserProfile(@RequestParam Long id) throws Exception {
        User user = userService.getProfile(id);
        return responseService.getSingleResult(new GetUserProfileResponseDto(user.getName(), user.getAddress(), user.getIntroduction(),user.getServices()));
    }

//    /**
//     * 1.6 본인 프로필 수정
//     */
//    @ApiOperation(value = "본인 프로필 수정", notes = "본인 프로필을 수정한다.")
//    @PatchMapping(value = "/updateUserProfile")
//    public SingleResult<UpdateUserResponseDto> updateUserProfile(@RequestBody @Valid UpdateUserRequestDto request) {
//        User updatedUser = userService.updateProfile(request);
//        return responseService.getSingleResult(new UpdateUserResponseDto(updatedUser.getName(), updatedUser.getAddress(), updatedUser.getIntroduction()));
//    }

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

    /**
     * 2. 찜내역(돌봄이) 반환
     */
    @ApiOperation(value = "2. 찜내역(돌봄이) 반환", notes = "찜내역(돌봄이)를 반환한다.")
    @GetMapping(value = "/getUserFavs")
    public SingleResult<RegisterCerResponseDto> getUserFavs(@RequestParam Long id) throws Exception {

        return responseService.getSingleResult(new RegisterCerResponseDto());
    }


    /**
     * 2.1 찜내역(돌봄이) 수정
     */
    @ApiOperation(value = "2.1 찜내역(돌봄이) 수정", notes = "찜내역(돌봄이)를 수정,삭제,생성한다.")
    @GetMapping(value = "/updateUserFav")
    public SingleResult<RegisterCerResponseDto> updateUserFav(@RequestBody @Valid RegisterCerRequestDto request) throws Exception {
        User user = userRepository.findById(request.getId()).orElseThrow(Exception::new);
        Certificate certificate = Certificate.createCer(user, request.getName(), request.getAgency(), request.getDate());
        userService.registerCertificate(certificate);
        return responseService.getSingleResult(new RegisterCerResponseDto(request.getName(), request.getAgency(), request.getDate()));
    }

    /**
     * 2.3 찜내역(동물) 수정
     */
    @ApiOperation(value = "2.3 찜내역(동물) 수정", notes = "찜내역(동물)를 수정,삭제,생성한다.")
    @GetMapping(value = "/updatePetFav")
    public SingleResult<RegisterCerResponseDto> updatePetFav(@RequestBody @Valid RegisterCerRequestDto request) throws Exception {
        User user = userRepository.findById(request.getId()).orElseThrow(Exception::new);
        Certificate certificate = Certificate.createCer(user, request.getName(), request.getAgency(), request.getDate());
        userService.registerCertificate(certificate);
        return responseService.getSingleResult(new RegisterCerResponseDto(request.getName(), request.getAgency(), request.getDate()));
    }

    /**
     * 2.2 찜내역(동물) 반환
     */
    @ApiOperation(value = "2.2 찜내역(동물) 반한", notes = "찜내역(동물)를 반환한다.")
    @GetMapping(value = "/getPetFavs")
    public SingleResult<RegisterCerResponseDto> GetPetFavs(@RequestBody @Valid RegisterCerRequestDto request) throws Exception {
        User user = userRepository.findById(request.getId()).orElseThrow(Exception::new);
        Certificate certificate = Certificate.createCer(user, request.getName(), request.getAgency(), request.getDate());
        userService.registerCertificate(certificate);
        return responseService.getSingleResult(new RegisterCerResponseDto(request.getName(), request.getAgency(), request.getDate()));
    }
}
