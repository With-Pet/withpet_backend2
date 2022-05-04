package com.withpet.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.withpet.backend.enumc.PetSex;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(of = {"name", "agency", "date"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "certificate")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id", length = 10, nullable = false, unique = true)
    private Long id;        //자격증 고유번호

    @Column(nullable = false, length = 50)
    private String name;    //자격증 명

    @Column(nullable = false, length = 50)
    private String agency;  //취득 기관

    @Column(nullable = false)
    private LocalDateTime date; //취득 날짜

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;     //유저 정보


    //==자격증 생성 메서드==//
    public static Certificate createCer(User user, String name, String agency, LocalDateTime date) {
        Certificate certificate = new Certificate();
        certificate.setUser(user);
        certificate.setName(name);
        certificate.setAgency(agency);
        certificate.setDate(date);
        return certificate;
    }
}
