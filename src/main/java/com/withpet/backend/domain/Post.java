package com.withpet.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.withpet.backend.enumc.PostType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(of = {"id", "title", "startTime", "endTime", "type", "price", "specifics", "description", "address", "x", "y", "isFavorite"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post extends CommonDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", length = 10, nullable = false, unique = true)
    private Long id;    //게시물 고유 번호

    @Column(length = 100, nullable = false)
    private String title;   //게시물 제목

    @Column(nullable = false)
    private LocalDateTime startTime;    //시작 날짜

    @Column(nullable = false)
    private LocalDateTime endTime;    //종료 날짜

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private PostType type;      //게시물 종류 W : 산책, E : 체험, C : 돌봄

    @Column(nullable = false)
    private Long price;         //시간당 이용 금액

    @Column(length = 1000)
    private String specifics;   //특이사항

    @Column(length = 1000)
    private String description;   //요청 사항

    @Column(nullable = false)
    private String address;    //게시물 지역 명

    @Column(length = 1000, nullable = false)
    private Float x;    //위도

    @Column(length = 1000, nullable = false)
    private Float y;    //경도

    @Column(length = 1, nullable = false)
    private Boolean isFavorite = false;    //즐겨찾기 여부 TRUE : O , FALSE : X default : FALSE

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;  //작성자 정보

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;   //펫 정보

    //==게시글 생성 메서드==//
    public static Post createPost(User user, Pet pet, String title,
                                  LocalDateTime startTime, LocalDateTime endTime,
                                  PostType type, Long price, String specifics,
                                  String description, String address, Float x, Float y) {
        Post post = new Post();
        post.setUser(user);
        post.setPet(pet);
        post.setTitle(title);
        post.setStartTime(startTime);
        post.setEndTime(endTime);
        post.setType(type);
        post.setPrice(price);
        post.setSpecifics(specifics);
        post.setDescription(description);
        post.setAddress(address);
        post.setX(x);
        post.setY(y);
        return post;
    }
}


//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "service_id")
//    private Service service;        //서비스 상세

//    //하나의 게시글이 여러 파일을 가지므로 N(postImage) : 1(post) 관계가 된다.
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "image_id")
//    private List<Image> postImage = new ArrayList<>();    //게시물 사진

//    // Post 에서 파일 처리 위함
//    public void addPhoto(Image postImage) {
//        this.postImage.add(postImage);
//
//        // 게시글에 파일이 저장되어있지 않은 경우
//        if(postImage.getPost() != this)
//            // 파일 저장
//            postImage.setPost(this);
//    }

