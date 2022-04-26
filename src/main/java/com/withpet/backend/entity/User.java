package com.withpet.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 유저 Entity(BaseEntity, UserDetails 상속)
 * BaseEntity : 공통적으로 포함되는 entity 요소를 분리
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(length = 5, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USR_ID", length = 100, nullable = false, unique = true)
    private String usrId;    //유저 아이디

    @Column(name = "USR_NAME", length = 30, nullable = false)
    private String usrName;  //유저 이름

    @Column(name = "USR_PASS", length = 200, nullable = false)
    private String usrPass;  //유저 비밀번호

    @Column(name = "USR_EMAIL", length = 50, nullable = false, unique = true)
    private String usrEmail; //유저 이메일

    @Column(name = "USR_TEL", length = 11, nullable = false, unique = true)
    private String usrTel;   //유저 핸드폰 번호

    @Column(name = "USR_TYPE", length = 1, nullable = false)
    private String usrType;  //회원 유형

    @Column(name = "USR_IMG", length = 100)
    private String usrImg;   //유저 프로필 이미지

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //중간 테이블을 만들지 않기 위함
    @ToString.Exclude  //stack overflow 제거
    private List<QandA> qandAList = new ArrayList<>(); //Q&A 정보

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Favorite> favoriteList = new ArrayList<>(); //즐겨찾기 정보


//    @Enumerated(EnumType.STRING)
//    @Column(name = "user_sns_type", length = 45, nullable = false)
//    private AuthProvider snsType; // 사용자 SNS 연동 타입 [local, naver, google, kakao]
//
//    @Column(name = "user_sns_key", length = 45, unique = true)
//    private String snsKey; // 사용자 SNS 고유 key

    @Builder
    public User(String usrId, String usrName, String usrPass, String usrEmail, String usrTel, String usrType, String usrImg) {
        this.usrId = usrId;
        this.usrName = usrName;
        this.usrPass = usrPass;
        this.usrEmail = usrEmail;
        this.usrTel = usrTel;
        this.usrType = usrType;
        this.usrImg = usrImg;
    }

    public static User of(User user) {
        return User.builder()
                .usrId(user.getUsrId())
                .usrName(user.getUsrName())
                .usrPass(user.getUsrPass())
                .usrEmail(user.getUsrEmail())
                .usrTel(user.getUsrTel())
                .usrType(user.getUsrType())
                .usrImg(user.getUsrImg())
                .build();
    }


    public static List<User> listOf(List<User> users) {
        return users.stream().map(User::of)
                .collect(Collectors.toList());
    }
}
