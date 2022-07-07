package com.withpet.backend.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post_fav")
public class PostFav {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_fav_id", length = 10, unique = true)
    private Long id;         //게시글 즐겨찾기 고유번호

    private Post post;

}
