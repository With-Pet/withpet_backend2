package com.withpet.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.withpet.backend.listener.FadEntityListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Q&A 정보 Entity
 */
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "qa")
@EntityListeners(value = { FadEntityListener.class })
public class QandA extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 5, nullable = false, unique = true)
    private int id;         //질의응답 고유 번호

    @Column(name = "QNA_Q", length = 1000, nullable = false)
    private String qnaQ;    //질문 내용

    @Column(name = "QNA_A", length = 1000)
    private String qnaA;    //답변 내용

    @Column(name = "USER_NAME", length = 50)
    private String userName;

    @JsonBackReference
    @ManyToOne
    @ToString.Exclude
    private Fad fad;

    @JsonBackReference
    @ManyToOne
    @ToString.Exclude
    private User user;
}
