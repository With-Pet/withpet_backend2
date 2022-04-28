package com.withpet.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController //ResponseBody -> data 자체를 바로 json or xml 로 보낼때 사용
@RequiredArgsConstructor
public class PetController {
//    /**
//     * 1.8 내 반려동물 등록
//     */
//    // @ApiOperation(value = "내 반려동물 등록", notes = "자신의 반려동물을 등록한다.")
//    @PostMapping(value = "/registerPet")
//    public SingleResult<User> registerPet(@PathVariable("aToken") String aToken,
//                                          @RequestParam @Valid RegisterPetDto request) throws Exception {
//
//
//        return responseService.getSingleResult(user);
//
//    }
}
