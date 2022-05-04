package com.withpet.backend.controller;

import com.withpet.backend.domain.Pet;
import com.withpet.backend.domain.User;
import com.withpet.backend.dto.pet.*;
import com.withpet.backend.dto.result.ListResult;
import com.withpet.backend.dto.result.SingleResult;
import com.withpet.backend.dto.user.UpdateUserRequestDto;
import com.withpet.backend.dto.user.UpdateUserResponseDto;
import com.withpet.backend.jwt.JwtUtils;
import com.withpet.backend.repository.UserRepository;
import com.withpet.backend.service.PetService;
import com.withpet.backend.service.ResponseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PetController {

    private final ResponseService responseService;
    private final UserRepository userRepository;
    private final PetService petService;

    /**
     * 1.7 내 반려동물 등록
     */
    @ApiOperation(value = "내 반려동물 등록", notes = "자신의 반려동물을 등록한다.")
    @PostMapping(value = "/registerPet")
    public SingleResult<RegisterPetResponseDto> registerPet(@RequestBody @Valid RegisterPetRequestDto request) throws Exception {
        //String snsId = JwtUtils.getUsername(request.getToken());
        //User user = userRepository.findBySnsId(snsId);
        User user = userRepository.findById(request.getId()).orElseThrow(Exception::new);
        Pet pet = Pet.createPet(user, request.getName(), request.getBirth(),
                request.getKind(), request.getNotes(), request.getWeight(), request.getIsNeutralization(), request.getSex());
        Pet registeredPet = petService.savePet(pet);
        return responseService.getSingleResult(new RegisterPetResponseDto(registeredPet.getName()));
    }

    /**
     * 1.8.1 선택한 반려동물 프로필 반환
     */
    @ApiOperation(value = "선택한 반려동물 프로필 반환", notes = "선택한 반려동물 프로필을 반환한다.")
    @GetMapping(value = "/getPetProfile")
    public SingleResult<GetPetResponseDto> getPetProfile(@RequestParam Long id) throws Exception {
        Pet pet = petService.getPetProfile(id);
        return responseService.getSingleResult(new GetPetResponseDto(pet.getName()));
    }

    /**
     * 1.8.2 회원 소유 전체 반려동물 프로필 반환
     */
    @ApiOperation(value = "전체 반려동물 프로필 반환", notes = "자신의 전체 반려동물 프로필을 반환한다.")
    @GetMapping(value = "/getAllPetProfile")
    public ListResult<Pet> getAllPetProfile(@RequestParam Long id) throws Exception {
//        String snsId = JwtUtils.getUsername(token);
//        User user = userRepository.findBySnsId(snsId);
        User user = userRepository.findById(id).orElseThrow(Exception::new);

        return responseService.getListResult(user.getPets());
    }

    /**
     * 1.9 반려동물 프로필 수정
     */
    @ApiOperation(value = "반려동물 프로필 수정", notes = "반려동물 프로필을 수정한다.")
    @PatchMapping(value = "/UpdatePetProfile")
    public SingleResult<UpdatePetResponseDto> updateUserProfile(@RequestBody @Valid UpdatePetRequestDto request) throws Exception {

        Pet updatedPet = petService.updatePetProfile(request);
        return responseService.getSingleResult(new UpdatePetResponseDto(updatedPet.getName()));
    }
}
