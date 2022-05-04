package com.withpet.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.withpet.backend.enumc.PetSex;
import com.withpet.backend.enumc.PetType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of = {"id", "name", "birth", "type", "kind", "notes", "weight", "isNeutralization", "sex"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pet")
public class Pet extends CommonDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id", length = 10, nullable = false, unique = true)
    private Long id;    //펫 고유번호

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;     //보호자 정보

    @Column(length = 100, nullable = false)
    private String name;    //펫 이름

    @Column(length = 11, nullable = false)
    private LocalDateTime birth;    //펫 생일

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private PetType type;   //펫 종류 D : 강아지, C : 고양이, E : 기타

    @Column(length = 100)
    private String kind;    //펫 품종

    @Column(length = 1000)
    private String notes;   //특이사항 및 참고사항

    @Column(length = 100)
    private Long weight;    //펫 몸무게

    private Boolean isNeutralization;   //중성화 여부

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetSex sex;     //펫 성별

    @JsonManagedReference
    @OneToMany(mappedBy = "pet") //post 에있는 owner 필드에 의해 매핑이 되어 있다.
    private List<Post> posts = new ArrayList<>();

    //==펫 생성 메서드==//
    public static Pet createPet(User user, String name, LocalDateTime birth, String kind, String notes, Long weight, Boolean isNeutralization, PetSex sex) {
        Pet pet = new Pet();
        pet.setOwner(user);
        pet.setName(name);
        pet.setBirth(birth);
        pet.setKind(kind);
        pet.setNotes(notes);
        pet.setWeight(weight);
        pet.setIsNeutralization(isNeutralization);
        pet.setSex(sex);
        return pet;
    }
}
