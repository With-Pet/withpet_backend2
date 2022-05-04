package com.withpet.backend.service;

import com.withpet.backend.domain.Pet;
import com.withpet.backend.domain.User;
import com.withpet.backend.dto.pet.UpdatePetRequestDto;
import com.withpet.backend.dto.user.UpdateUserRequestDto;
import com.withpet.backend.jwt.JwtUtils;
import com.withpet.backend.repository.PetRepository;
import com.withpet.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    /**
     * 유저 등록
     *
     * @return 유저 권한을 가지고 있는 유저
     */
    @Transactional
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    /**
     * 펫 프로필 반환
     */
    @Transactional
    public Pet getPetProfile(Long id) throws Exception {
        return petRepository.findById(id).orElseThrow(Exception::new);
    }

    /**
     * 펫 수정
     * 변경감지 적용
     */
    @Transactional
    public Pet updatePetProfile(UpdatePetRequestDto request) throws Exception {
        Pet pet = petRepository.findById(request.getId()).orElseThrow(Exception::new);
        pet.setName(request.getName());
        pet.setBirth(request.getBirth());
        pet.setType(request.getType());
        pet.setKind(request.getKind());
        pet.setNotes(request.getNotes());
        pet.setWeight(request.getWeight());
        pet.setIsNeutralization(request.getIsNeutralization());
        pet.setSex(request.getSex());
        return pet;
    }
}
