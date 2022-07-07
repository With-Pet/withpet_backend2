package com.withpet.backend.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_fav")
public class UserFav extends CommonDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_fav_id", length = 10, unique = true)
    private Long id;         //유저 즐겨찾기 고유번호

    private User user;


}
