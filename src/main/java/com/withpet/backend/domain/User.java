package com.withpet.backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "snsId", "name", "provider", "address", "x", "y", "countWalk", "countCaring", "countExperience", "introduction", "token"})
@Table(name = "user")
public class User extends CommonDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length = 10, unique = true)
    private Long id;         //회원 고유번호

    @Column(nullable = false, length = 100)
    private String name;     //닉네임

    @Column(length = 100)
    private String password; //임시 - 회원 비밀번호

    @Column(length = 100, unique = true)
    private String snsId;    //회원 아이디, 모두 SNS 계정 이므로 ID = EMAIL

    @Column(length = 10)
    private String provider; //가입한 SNS 종류 : kakao, apple

    //private String image;  //유저 프로필 이미지

    @Column(length = 100)
    private String address;  //회원 주소

    @Column(length = 1000)
    private Float x;        //경도

    @Column(length = 1000)
    private Float y;        //위도

    @Column(length = 100)
    private Integer countWalk;  //산책 카운트

    @Column(length = 100)
    private Integer countCaring; //돌봄 카운트

    @Column(length = 100)
    private Integer countExperience; //체험 카운트

    @Column(length = 1000)
    private String introduction;    //유저 자기소개

    private String token;    //FCM 토큰 정보

    /**
     * 컬렉션은 필드에서 바로 초기화 하는 것이 안전하다.
     * null 문제에서 안전하다.
     * 하이버네이트는 엔티티를 영속화 할 때, 컬랙션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변경한다.
     * 만약 임의의 메서드에서 컬력션을 잘못 생성하면 하이버네이트 내부 메커니즘에 문 제가 발생할 수 있다.
     * 따라서 필드레벨에서 생성하는 것이 가장 안전하고, 코드도 간결하다.
     * JsonManagedReference : 양방향 관계에서 정방향 참조할 변수에 추가하면 직렬화에 포함 (부모에 해당하는 곳에)
     * JsonBackReference :  양방향 관계에서 역방향 참조로, 추가하면 직렬화에서 제외된다.(자식에 해당하는 곳에)
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "user")   //post 에있는 user 필드에 의해 매핑이 되어 있다.
    private List<Post> posts = new ArrayList<>();   //회원이 작성한 게시글

    @JsonManagedReference
    @OneToMany(mappedBy = "owner")  //pet 에있는 owner 필드에 의해 매핑이 되어 있다.
    private List<Pet> pets = new ArrayList<>();     //회원 소유 펫

    @JsonManagedReference
    @OneToMany(mappedBy = "user")  //certificate 에있는 user 필드에 의해 매핑이 되어 있다.
    private List<Certificate> certificates = new ArrayList<>();     //회원 소유 자격증

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Service> services = new ArrayList<>();     //회원 이용가능 서비스


    @Builder
    public User(String name, String password, String snsId, String provider, String address, Float x, Float y, String introduction) {
        this.name = name;
        this.password = password;
        this.snsId = snsId;
        this.provider = provider;
        this.address = address;
        this.x = x;
        this.y = y;
        this.introduction = introduction;

    }
}
