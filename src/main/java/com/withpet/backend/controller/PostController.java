package com.withpet.backend.controller;

import com.withpet.backend.domain.Pet;
import com.withpet.backend.domain.Post;
import com.withpet.backend.dto.pet.GetDetailPostResponseDto;
import com.withpet.backend.dto.post.GetPostResponseDto;
import com.withpet.backend.dto.post.RegisterPostRequestDto;
import com.withpet.backend.dto.post.RegisterPostResponseDto;
import com.withpet.backend.dto.result.ListResult;
import com.withpet.backend.dto.result.SingleResult;
import com.withpet.backend.repository.PetRepository;
import com.withpet.backend.repository.UserRepository;
import com.withpet.backend.service.PostService;
import com.withpet.backend.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Api(tags = {"3. Board"})
@RequiredArgsConstructor
@RestController
@RequestMapping
public class PostController {

    private final ResponseService responseService;
    private final PostService postService;
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    /**
     * 3.1 서비스 등록
     */
    @ApiOperation(value = "서비스 등록", notes = "돌봄 산책 체험 서비스를 등록한다.")
    @PostMapping(value = "/RegisterService")
    public SingleResult<RegisterPostResponseDto> registerService(@RequestBody @Valid RegisterPostRequestDto post) throws Exception {
        Post registeredPost = postService.writePost(post);
        return responseService.getSingleResult(new RegisterPostResponseDto(registeredPost.getId(),registeredPost.getTitle()));
    }

    /**
     * 3.2
     * @return
     */
    @ApiOperation(value = "맡기기 리스트 반환", notes = "맡기기 리스트를 반환한다.")
    @GetMapping(value = "/GetList")
    public ListResult<GetPostResponseDto> getList()
    {
        List<Post> posts = postService.getAllPost();
        List<GetPostResponseDto> resultList = Arrays.asList(modelMapper.map(posts, GetPostResponseDto[].class));
        return responseService.getListResult(resultList);
    }
//    /**3.5
//
//    **/
//
//    public SingleResult<Post> returnDetail(@RequestParam Long postId) {
//        Post post = postService.returnDetail(postId);
//        Pet pet = petRepository.findById(post.getPet().getId()).orElseThrow();
//        return responseService.getSingleResult(new GetDetailPostResponseDto(post.getId()));
//    }

    /**
     * 3.3
     * @param query
     * @return
     */
    @ApiOperation(value = "주소 검색", notes = "주소검색을 통해 주소 리스트를 불러온다.")
    @GetMapping(value = "/SearchAddress")
    public SingleResult<Object> searchAddress(@RequestParam(value = "query") String query) {
        return responseService.getSingleResult(postService.searchAddress(query));
    }

    /**
     * 3.4
     * @param id
     * @param address
     * @param x
     * @param y
     * @return
     */
    @ApiOperation(value = "주소 변경", notes = "주소를 선택하여 변경한다.")
    @GetMapping(value = "/AppliedAddress")
    public SingleResult<Object> appliedAddress(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "x") Float x,
            @RequestParam(value = "y") Float y) {
        return responseService.getSingleResult(postService.appliedAddress(id,address, x, y));
    }



//    @ApiOperation(value = "예약 내역 반환", notes = "예약 상태에 따라 돌봄을 신청한 내역, 돌봄을 받은 내역으로 구분한다." +
//                                                 "B : 이용 전, U : 이용 중, A : 이용 후")
//    @GetMapping(value = "/returnReservation")
//    public ListResult<Post> returnReservation() {
//        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        //String id = authentication.getName(); // 쓰레드 로컬에서 관리함, userdetail의 값을 사용한다. snsid
//        return responseService.getListResult(postService.returnReservation("1"));
//    }

    @ApiOperation(value = "예약 내역 반환", notes = "")
    @GetMapping(value = "/returnReservationDetail/{postId}")
    public SingleResult<Post> returnReservationDetail(Long postId) {
        return responseService.getSingleResult(postService.returnReservationDetail(postId));
    }


//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public int create(
//            @RequestParam(value="image", required=false) List<MultipartFile> files,
//            @RequestParam(value="id") String id,
//            @RequestParam(value="title") String title,
//            @RequestParam(value="content") String content
//    ) throws Exception {
//
//        User user = userRepository.findById(id).orElseThrow(CUserNotFoundException::new);
//
//        ParamsPost requestDto =
//                Post.builder()
//                        .user(user)
//                        .build();
//
//        return postService.create(requestDto, files);
//    }


//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "게시글 수정", notes = "게시판의 글을 수정한다.")
//    @PutMapping(value = "/post/{postId}")
//    public SingleResult<Post> post(@PathVariable long postId, @Valid @ModelAttribute ParamsPost post) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String uid = authentication.getName();
//        return responseService.getSingleResult(boardService.updatePost(postId, uid, post));
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
//    @DeleteMapping(value = "/post/{postId}")
//    public CommonResult deletePost(@PathVariable long postId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String uid = authentication.getName();
//        boardService.deletePost(postId, uid);
//        return responseService.getSuccessResult();
//    }
}