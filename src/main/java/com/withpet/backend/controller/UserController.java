package com.withpet.backend.controller;

import com.withpet.backend.domain.User;
import com.withpet.backend.dto.result.SingleResult;
import com.withpet.backend.dto.user.UpdateUserRequestDto;
import com.withpet.backend.dto.user.UpdateUserResponseDto;
import com.withpet.backend.dto.user.UserResponseDto;
import com.withpet.backend.repository.UserRepository;
import com.withpet.backend.service.ResponseService;
import com.withpet.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController //ResponseBody -> data 자체를 바로 json or xml 로 보낼때 사용
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
    public SingleResult<UserResponseDto> getUserProfile(@RequestParam String aToken){
        User user = userService.getProfile(aToken);
        return responseService.getSingleResult(new UserResponseDto(user.getId(),user.getName()));
    }

    /**
     * 1.7 본인 프로필 수정
     */
    @ApiOperation(value = "본인 프로필 수정", notes = "본인 프로필을 수정한다.")
    @PatchMapping(value = "/updateUserProfile")
    public SingleResult<UpdateUserResponseDto> updateUserProfile(@RequestBody @Valid UpdateUserRequestDto request) {

        User updatedUser = userService.updateProfile(request);
        return responseService.getSingleResult(new UpdateUserResponseDto(updatedUser.getName(),updatedUser.getAddress(),updatedUser.getIntroduction()));
    }
}
