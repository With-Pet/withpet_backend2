package com.withpet.backend.controller;

import com.withpet.backend.domain.Pet;
import com.withpet.backend.domain.Post;
import com.withpet.backend.domain.Service;
import com.withpet.backend.domain.User;
import com.withpet.backend.dto.result.ListResult;
import com.withpet.backend.dto.result.SingleResult;
import com.withpet.backend.dto.user.GetUserProfileResponseDto;
import com.withpet.backend.repository.PetRepository;
import com.withpet.backend.repository.PostRepository;
import com.withpet.backend.repository.ServiceRepository;
import com.withpet.backend.repository.UserRepository;
import com.withpet.backend.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Dev Controller"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dev")
public class DevController {

    private final UserRepository userRepository;
    private final ResponseService responseService;
    private final PetRepository petRepository;
    private final PostRepository postRepository;
    private final ServiceRepository serviceRepository;

    /**
     * 전체 회원 반환 (일부로 Entity 전체 반환)
     */
    @ApiOperation(value = "(DEV)전체 회원 반환", notes = "(DEV)전체 회원을 반환합니다.")
    @GetMapping(value = "/getAllUser")
    public ListResult<User> getAllUserProfile() {

        return responseService.getListResult(userRepository.findAll());
    }

    /**
     * 전체 펫 반환 (일부로 Entity 전체 반환)
     */
    @ApiOperation(value = "(DEV)전체 펫 반환", notes = "(DEV)전체 펫을 반환합니다.")
    @GetMapping(value = "/getAllPet")
    public ListResult<Pet> getAllPetProfile() {

        return responseService.getListResult(petRepository.findAll());
    }

    /**
     * 전체 게시물 반환 (일부로 Entity 전체 반환)
     */
    @ApiOperation(value = "(DEV)전체 게시물 반환", notes = "(DEV)전체 게시물을 반환합니다.")
    @GetMapping(value = "/getAllPost")
    public ListResult<Post> getAllPost() {

        return responseService.getListResult(postRepository.findAll());
    }

    /**
     * 전체 서비스 반환 (일부로 Entity 전체 반환)
     */
    @ApiOperation(value = "(DEV)전체 서비스 반환", notes = "(DEV)전체 서비스를 반환합니다.")
    @GetMapping(value = "/getAllService")
    public ListResult<Service> getAllService() {

        return responseService.getListResult(serviceRepository.findAll());
    }
}
