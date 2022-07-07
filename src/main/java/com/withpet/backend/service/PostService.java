package com.withpet.backend.service;

import com.withpet.backend.domain.Pet;
import com.withpet.backend.domain.Post;
import com.withpet.backend.domain.User;
import com.withpet.backend.dto.post.KaKaoAddressResult;
import com.withpet.backend.dto.post.KaKaoResult;
import com.withpet.backend.dto.post.RegisterPostRequestDto;
import com.withpet.backend.repository.PetRepository;
import com.withpet.backend.repository.PostRepository;
import com.withpet.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PetRepository petRepository;

    /**
     * 전체 게시글을 반환합니다.
     *
     */
    @Transactional
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    /**
     * 선택한 게시글을 반환합니다.
     * //@Cacheable(value = CacheKey.POST, key = "#postId", unless = "#result == null")
     */
    @Transactional
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    /**
     * 게시글을 등록합니다. 게시글의 회원 UID가 조회되지 않으면 CUserNotFoundException 처리합니다.
     */
    //@CacheEvict(value = CacheKey.POSTS, key = "#boardName")
    //@ForbiddenWordCheck
    @Transactional
    public Post writePost(
            RegisterPostRequestDto postRequestDto) throws Exception {
        User user = userRepository.findById(postRequestDto.getId()).orElseThrow();
        Pet pet = petRepository.findById(postRequestDto.getPetId()).orElseThrow();
        Post post = Post.createPost(user, pet, postRequestDto.getTitle(), postRequestDto.getStartDate(),
                postRequestDto.getEndDate(), postRequestDto.getType(), postRequestDto.getPrice(),
                postRequestDto.getSpecifics(), postRequestDto.getDescription(),
                postRequestDto.getAddress(), postRequestDto.getX(), postRequestDto.getY());

        return postRepository.save(post);
    }

    //게시글을 수정합니다. 게시글 등록자와 로그인 회원정보가 틀리면 CNotOwnerException 처리합니다.
    //@CachePut(value = CacheKey.POST, key = "#postId") 갱신된 정보만 캐시할경우에만 사용!
    //@ForbiddenWordCheck
    @Transactional
    public Post updatePost(Long postId, String id, RegisterPostRequestDto postRequestDto) {
        Post post = getPost(postId);
        User user = post.getUser();
//        if (!id.equals(user.getId()))
//            throw new CNotOwnerException();

        // 영속성 컨텍스트의 변경감지(dirty checking) 기능에 의해 조회한 Post내용을 변경만 해도 Update쿼리가 실행됩니다.
//        post.setUpdate(paramsPost.getPostAuthor(), paramsPost.getPostTitle());
//        cacheSevice.deleteBoardCache(post.getPostId(), post.getBoard().getName());
        return post;
    }

    // 게시글을 삭제합니다. 게시글 등록자와 로그인 회원정보가 틀리면 CNotOwnerException 처리합니다.
    @Transactional
    public boolean deletePost(Long postId, String uid) {
        Post post = getPost(postId);
        User user = post.getUser();
//        if (!uid.equals(user.getId()))
//            throw new CNotOwnerException();
        postRepository.delete(post);
//        cacheSevice.deleteBoardCache(post.getPostId(), post.getBoard().getName());
        return true;
    }

    /*
    List<MultipartFile> 을 전달받아 파일을 저장한 후
    관련 정보를 List<PostImage>로 변환하여 반환할 FileHandler를 생성하고,
    반환받은 파일 정보를 저장하기 위하여 PostService를 수정한다.
    */
//    private List<Image> create(List<MultipartFile> files) throws Exception {
//        // 파일 처리를 위한 Board 객체 생성
//        Post post = new Post();
//
//        List<Image> photoList = fileHandler.parseFileInfo(files);
//
//        // 파일이 존재할 때에만 처리
//        if(!photoList.isEmpty()) {
//            for(Image postImage : photoList) {
//                // 파일을 DB에 저장
//                post.addPhoto(postImageRepository.save(postImage));
//            }
//        }
//        return postRepository.save(post).getPostImage();
//    }
    /*
    카카오 주소검색 api로 부터 주소명, 주소타입, x좌푶, y좌표 값을 가져온다.
     */
    @Transactional
    public Object searchAddress(String query) {
        Mono<Object> mono = WebClient.builder().baseUrl("https://dapi.kakao.com")
                .build().get()
                .uri(builder -> builder.path("/v2/local/search/address.json")
                        .queryParam("query", query)
                        .build()
                )
                .header("Authorization", "KakaoAK" + " " + "b6f8901a70131b1f410a4229691dbc19")
                .exchangeToMono(response -> {
                    return response.bodyToMono(KaKaoResult.class);
                });
        return mono.block();
    }

    /**
     * 선택한 주소로 검색 주소 및 x, y 좌표를 변경한다.
     *
     * @param address 주소명
     * @param x       x좌표
     * @param y       y좌표
     */
    @Transactional
    public Object appliedAddress(Long id, String address, Float x, Float y) {
        if (address.equals("gps")) {
            Mono<Object> mono = WebClient.builder().baseUrl("https://dapi.kakao.com")
                    .build().get()
                    .uri(builder -> builder.path("/v2/local/geo/coord2address.json")
                            .queryParam("x", x)
                            .queryParam("y", y)
                            .queryParam("input_coord", "WGS84")
                            .build()
                    )
                    .header("Authorization", "KakaoAK" + " " + "b6f8901a70131b1f410a4229691dbc19")
                    .exchangeToMono(response -> {
                        User user = userRepository.findById(id).orElseThrow();
                        user.setX(x);
                        user.setY(y);
                        return response.bodyToMono(KaKaoAddressResult.class);
                    });
            return mono.block();

        } else {
            User user = userRepository.findById(id).orElseThrow();
            user.setAddress(address);
            user.setX(x);
            user.setY(y);
            return user;
        }
    }

    @Transactional
    public Post returnDetail(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

//    @Transactional
//    public List<Post> returnReservation(String id) {
//        User user = userRepository.findById(id).orElseThrow(CUserNotFoundException::new);
//        return user.getPosts();
//    }

    @Transactional
    public Post returnReservationDetail(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        return post;
    }
}