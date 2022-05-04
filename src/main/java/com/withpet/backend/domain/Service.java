package com.withpet.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter @Setter
@ToString(of = {"id", "service"})
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", length = 10, unique = true)
    private Long id;         //서비스 고유번호

    @ElementCollection()
    private Map<String,Boolean> service;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;     //유저 정보

    //==서비스 생성 메서드==//
    public static Service createService(User user, Map<String,Boolean> service1) {
        Service service = new Service();
        service.setUser(user);
        service.setService(service1);
        return service;
    }
}
